import React, {Component, Fragment} from 'react'
import styles from './RectBtn.module.css'

export default class RectBtn extends Component {
    render() {
        return(
            <div className={styles.btn}>
                <span className={styles.btnText}>가입 완료</span>
            </div>
        )
    }
};