import React, {Component} from 'react'
import styles from './Term.module.css'

export default class Term extends Component {

    openTerm = () => {
        window.open("/term")
    }

    render() {
        return (
            <div className={styles.container}>
                <div className={[styles.agreeContainer, styles.inlineBlock].join(' ')} onClick={this.props.onClick}>
                    <div className={this.props.agree ? [styles.circle, styles.agree].join(' ') : styles.circle}/>
                    <span className={this.props.agree ? [styles.termAgree, styles.agree].join(' ') : styles.termAgree}>
                    약관 전체 동의
                    </span>
                </div>
                <div className={[styles.termViewContainer, styles.inlineBlock].join(' ')}
                     onClick={this.openTerm}>
                <span className={styles.termView}>
                    약관보기
                </span>
                    <img className={styles.termViewImg} src="/image/right-arrow.svg"/>
                </div>
            </div>
        )
    }
};
