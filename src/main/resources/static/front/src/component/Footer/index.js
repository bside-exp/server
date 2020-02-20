import React, {Component} from 'react'
import styles from './index.css'

export default class Footer extends Component {

    render() {

        return (
            <div className={styles.container}>
                <div className={styles.text}>ⓒ경험공유 Corp.</div>
            </div>
        )
    }
}