import React, {Component, Fragment} from 'react'
import styles from './Password.module.css'

export default class Password extends Component{

    state = {
        inputType: 'password',
        img: '/image/eye.svg',
        no: 0
    }

    types = ['password','text'];
    imgs = ['/image/eye.svg','/image/eye-slashed.svg']

    changeType = () => {
        const no = (this.state.no + 1) % 2
        this.setState({
            no: no,
            inputType: this.types[no],
            img: this.imgs[no]
        })
    }

    onChange = (e) => {
        this.props.onChange(e.target.value)
    }

    render() {
        return(
            <Fragment>
                <form className={styles.container}>
                    <input className={this.props.conform ? styles.textInput : [styles.textInput, styles.different].join(' ')} type={this.state.inputType}
                           placeholder="비밀번호" onChange={this.onChange}/>
                    <img className={styles.eyeInText} src={this.state.img} onClick={this.changeType}/>
                    <div className={styles.error} style={{display: this.props.conform ? 'none' : 'block'}}>
                        문자, 숫자 조합으로 6자~10자로 입력해주세요
                    </div>
                </form>
            </Fragment>
        )
    }
}
