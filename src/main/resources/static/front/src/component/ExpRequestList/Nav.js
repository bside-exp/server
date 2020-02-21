import React, {Component} from 'react'
import styles from './Nav.css'

export default class Nav extends Component {

    render() {
        const sidebar = this.props.sidebar;

        return (
            <div className={styles.Nav}>
                <div className={styles.left}>
                    <img className={styles.img} src="/image/gnb-back-w.svg"/>
                </div>
                <div className={styles.center}>
                    <div className={styles['center-table']}>
                        <div className={styles['center-text']}>
                            경험 제공
                        </div>
                    </div>
                </div>
                <div className={styles.right} onClick={sidebar}>
                    <img className={styles.img} src="/image/combined-shape-w.svg"/>
                </div>
            </div>
        );
    }
}