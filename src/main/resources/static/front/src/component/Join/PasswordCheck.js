import React, {Component, Fragment} from 'react'
import styles from './PasswordCheck.module.css'

export default class PasswordCheck extends Component{
    input = null
    state = {
        inputType: 'password',
        img: '/image/eye.svg',
        no: 0,
        passwordCheck: ''
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
        this.setState({
            ...this.state,
            passwordCheck: e.target.value
        })
    }

    onDelete = () => {
        this.props.onChange('')
        this.setState({
            ...this.state,
            passwordCheck: '',
        })
        this.input.value = ''
    }
    render() {
        return(
            <Fragment>
                <form className={styles.container}>
                    <input className={this.props.same ? styles.textInput : [styles.textInput, styles.different].join(' ')}
                           ref={ref => {this.input = ref}}
                           type={this.state.inputType} placeholder="비밀번호 확인" onChange={this.onChange}/>
                    <img className={styles.eyeInText} src={this.state.img} onClick={this.changeType}/>
                    <img src='/image/x2.svg' className={styles.x} onClick={this.onDelete} style={{display: this.state.passwordCheck.length == 0 ? 'none' : 'block'}}/>
                    <div className={styles.error} style={{display: this.props.same ? 'none' : 'block'}}>
                        비밀번호가 일치하지 않습니다.
                    </div>
                </form>
            </Fragment>
        )
    }
}
