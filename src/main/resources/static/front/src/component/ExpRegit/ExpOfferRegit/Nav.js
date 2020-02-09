import React, {Component} from 'react'
import styles from './Nav.module.css'

export default class Nav extends Component {

    submit = () => {
        this.props.submit()
    }

    render() {


        return (
            <nav className={styles.nav}>
                <div className={styles["btn-box"]} onClick={this.props.cancel}>
                    <div className={styles["btn-table"]}>
                        <div className={styles.leftBtn}>취소</div>
                    </div>
                </div>
                <div className={styles["title-box"]}>
                    <div className={styles["title-table"]}>
                        <div className={styles.title}>경험 제공</div>
                    </div>
                </div>
                <div className={styles["btn-box"]} onClick={this.submit}>
                    <div className={styles["btn-table"]}>
                        <div className={styles.rightBtn}>완료</div>
                    </div>
                </div>
            </nav>
        )
    }
}