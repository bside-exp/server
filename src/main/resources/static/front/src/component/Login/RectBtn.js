import React, {Component} from 'react'
import styles from './RectBtn.module.css'

export default class RectBtn extends Component {

    render() {
        const {email, password} = this.props.state
        const checked = (email.length != 0 && password.length != 0)
        return (
            <div className={checked ? [styles.btn, styles.checked].join(' ') : styles.btn} onClick={this.props.onClick}>
                <span className={styles.btnText}>로그인</span>
            </div>
        )
    }
};