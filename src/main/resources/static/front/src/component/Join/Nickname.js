import React, {Component, Fragment} from 'react'
import styles from './Nickname.module.css'

export default class Nickname extends Component{

    render() {
        return(
            <Fragment>
                <form className={styles.container}>
                    <input className={styles.textInput} type="text" placeholder="닉네임"/>
                    <div className={styles.checkBtn}>
                        <span className={styles.checkBtnText}>
                            중복확인
                        </span>
                    </div>
                </form>
            </Fragment>
        )
    }
}
