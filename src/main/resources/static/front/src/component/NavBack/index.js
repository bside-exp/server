import React, {Component} from 'react'
import styles from './index.css'

export default class NavBack extends Component {

    left = () => {
        const call = this.props.left ? this.props.left : history.back()
        call()
    }

    right = () => {
        this.props.right()
    }

    render() {
        const propStyle = this.props.style ? this.props.style : ''

        return (
            <div className={[styles.Nav, propStyle].join(' ')}>
                <div className={styles.left} onClick={this.left}>
                    <img className={styles.img} src="/image/gnb-back-w.svg"/>
                </div>
                <div className={styles.center}>
                    <div className={styles['center-table']}>
                        <div className={styles['center-text']}>
                            {this.props.children}
                        </div>
                    </div>
                </div>
                <div className={styles.right} onClick={this.right}>
                    <img className={styles.img} src="/image/combined-shape-w.svg"/>
                </div>
            </div>
        );
    }
}