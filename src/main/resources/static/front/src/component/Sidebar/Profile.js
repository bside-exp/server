import React, {Component} from 'react'
import jwt_decode from 'jwt-decode'
import styles from './Profile.module.css'

export default class Profile extends Component {

    login = () => {
        window.open("/login")
    }

    render() {

        const token = localStorage.getItem("expAccessToken")

        if (token) {
            const decodedToken = jwt_decode(token)
            var box = (
                <div>
                    <span className={styles.nickname}>{decodedToken.nickname}</span>
                    <span className={styles.email}>{decodedToken.email}</span>
                    <img src="/image/config.svg" className={styles.config}/>
                </div>
            )
        } else {
            var box = (
                <div>
                    <span className={[styles.nickname, styles.pointer].join(' ')} onClick={this.login}>로그인 해주세요 <img
                        className={styles.ra} src="/image/right-arrow.svg"/></span>
                    <span className={styles.email}>로그인하시고 경험을 요청하세요</span>
                </div>
            )
        }

        return (
            <div className={styles.box}>
                {box}
            </div>
        )
    }
}