import React, {Component, Fragment} from 'react'
import styles from './Password.module.css'

export default class Password extends Component {
    state = {
        inputType: 'password',
        img: '/image/eye.svg',
        no: 0,
        password: ''
    }

    types = ['password', 'text'];
    imgs = ['/image/eye.svg', '/image/eye-slashed.svg']

    changeType = () => {
        const no = (this.state.no + 1) % 2
        this.setState({
            no: no,
            inputType: this.types[no],
            img: this.imgs[no]
        })
    }

    onChange = (e) => {
        this.props.onChange(e)
        this.setState({
            ...this.state,
            password: e.target.value
        })
    }

    render() {
        return (
            <Fragment>
                <div className={styles.container}>
                    <form className={styles.form}>
                        <input className={styles.textInput} type={this.state.inputType}
                               placeholder="비밀번호" onChange={this.onChange}/>
                        <img className={styles.eyeInText} src={this.state.img} onClick={this.changeType}/>
                    </form>
                </div>
            </Fragment>
        )
    }
}
