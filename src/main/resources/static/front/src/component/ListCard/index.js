import React, {Component} from 'react'
import styles from './index.css'

export default class ListCard extends Component {

    call = () => {
        this.props.onClick()
    }

    render() {

        const st = this.props.style ? this.props.style : '';

        return (
            <div className={[styles.card, st].join(' ')} onClick={this.call}>
                <div className={styles.title}>{this.props.title}</div>
                <div className={styles.content}>{this.props.content}</div>
                <div className={styles.user}>
                    {this.props.bottom}
                </div>
            </div>
        )
    }
}