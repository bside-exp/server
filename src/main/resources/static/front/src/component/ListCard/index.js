import React, {Component} from 'react'
import styles from './index.css'

export default class ListCard extends Component {

    render() {

        const st = this.props.style ? this.props.style : '';

        return (
            <div className={[styles.card, st].join(' ')}>
                <div className={styles.title}>{this.props.title}</div>
                <div className={styles.content}>{this.props.content}</div>
                <div className={styles.user}>
                    {this.props.firm}・{this.props.duration}개월・{this.props.type}
                </div>
            </div>
        )
    }
}