import React, {Component, Fragment} from 'react'
import styles from './PasswordCheck.module.css'

export default class PasswordCheck extends Component{

    render() {
        return(
            <Fragment>
                <form className={styles.container}>
                    <input className={styles.textInput} type="text" placeholder="비밀번호"/>
                    <img className={styles.eyeInText} src='/image/eye-slashed.svg'/>
                </form>
            </Fragment>
        )
    }
}
