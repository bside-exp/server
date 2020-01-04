import React, {Component, Fragment} from 'react'
import styles from './Term.module.css'

export default class Term extends Component {
    render() {
        return (
            <div className={styles.container}>
                <div className={[styles.agreeContainer, styles.inlineBlock].join(' ')}>
                    <div className={styles.circle}/>
                    <span className={styles.termAgree}>
                    약관 전체 동의
                    </span>
                </div>
                <div className={[styles.termViewContainer, styles.inlineBlock].join(' ')}>
                <span className={styles.termView}>
                    약관보기
                </span>
                    <img className={styles.termViewImg} src="/image/right-arrow.svg"/>
                </div>
            </div>
        )
    }
};
