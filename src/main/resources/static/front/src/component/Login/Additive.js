import React, {Component} from 'react'
import styles from './Additive.module.css'

export default class Additive extends Component {

    openJoin = () => {
        window.open("/join")
    }

    openFindPassword = () => {
        window.open("/password/find")
    }

    render() {
        return (
            <div className={styles.container}>
                <div className={[styles.findPassword, styles.inlineBlock].join(' ')}
                     onClick={this.openFindPassword}>
                    <span className={styles.additiveText}>
                        비밀번호 찾기
                    </span>
                    <img className={styles.arrowRight} src="/image/right-arrow.svg"/>
                </div>
                <div className={[styles.join, styles.inlineBlock].join(' ')}
                     onClick={this.openJoin}>
                    <span className={styles.additiveText}>
                        회원가입
                    </span>
                    <img className={styles.arrowRight} src="/image/right-arrow.svg"/>
                </div>
            </div>
        )
    }
};
