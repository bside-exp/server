import React, {Component} from 'react'
import styles from './plain.module.css'

export default class Plain extends Component {

    render() {

        return (
            <div className={styles.container}>
                <div className={styles.title}>
                    {this.props.title}
                </div>
                <div className={styles.content}>
                    {this.props.content}
                </div>
            </div>
        )
    }
}