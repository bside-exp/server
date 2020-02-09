import React, {Component} from 'react'
import styles from './Title.css'

export default class TextInput extends Component {

    onChange = (e) => {
        this.props.func(e.target.value)
    }


    render() {

        const placeHolder = this.props.placeHolder;
        const label = this.props.label;
        const paddingTop = this.props.paddingTop ? this.props.paddingTop : '';

        return (
            <div className={[styles.container, paddingTop].join(' ')}>
                <span className={styles.title}>{label}</span>
                <form className={styles["content-box"]}>
                    <input className={styles.content} type='text'
                           placeholder={placeHolder} onChange={this.onChange}/>
                </form>
                <div className={styles.line}></div>
            </div>
        )
    }
}