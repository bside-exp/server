import React, {Component, Fragment} from 'react'
import styles from './index.module.css'


export default class Nav extends Component {

    render() {

        const cls = this.props.class

        return (
            <Fragment>
                <nav className={[styles.nav, cls].join(' ')}>
                    <div className={styles.leftBtn}></div>
                    <div className={styles.title}>
                        <div className={styles.table}>
                            <div className={styles.text}>
                                {this.props.children}
                            </div>
                        </div>
                    </div>
                    <div className={styles.rightBtn} onClick={this.props.onRight}>
                        <img src="/image/x2.svg"/>
                    </div>
                </nav>
            </Fragment>
        )
    }
}
