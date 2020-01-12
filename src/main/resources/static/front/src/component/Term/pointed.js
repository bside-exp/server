import React, {Component} from 'react'
import styles from './pointed.module.css'

export default class Pointed extends Component {

    render() {
        const content = this.props.content

        const contents = content.map((item) => {
            return (
                <div className={styles.contentWrap}>
                    <div className={styles.point}>
                        {item.point}
                    </div>
                    <div className={styles.content}>
                        {item.content}
                    </div>
                </div>
            )
        })

        return (
            <div className={styles.container}>
                <div className={styles.title}>
                    {this.props.title}
                </div>
                {contents}
            </div>
        )
    }
};
