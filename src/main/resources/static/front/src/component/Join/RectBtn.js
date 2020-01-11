import React, {Component} from 'react'
import styles from './RectBtn.module.css'

export default class RectBtn extends Component {

    render() {
        const {email, password, passwordCheck, nickname, termAgree, isPasswordCheckSame, isPasswordConform} = this.props.state
        const checked = (email.length != 0 && nickname.length != 0 && password.length != 0 && passwordCheck.length != 0
            && termAgree && isPasswordCheckSame && isPasswordConform)
        return(
            <div className={checked ? [styles.btn, styles.checked].join(' ') : styles.btn} onClick={this.props.onClick}>
                <span className={styles.btnText}>가입 완료</span>
            </div>
        )
    }
};