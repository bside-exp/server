import React, {Component} from 'react'
import styles from './MultilineTextInput.css'

export default class TextInput extends Component {

    onChange = (e) => {
        this.props.func(e.target.value)
        let element = document.getElementById("text")
        let nextHeight = element.scrollHeight - 14;
        nextHeight = nextHeight >= 24 ? nextHeight + 'px' : '24px'
        element.style.height = nextHeight;
    }

    render() {

        const placeHolder = this.props.placeHolder;
        const label = this.props.label;
        const paddingTop = this.props.paddingTop ? this.props.paddingTop : '';

        return (
            <div className={[styles.container, paddingTop].join(' ')}>
                <span className={styles.title}>{label}</span>
                <form className={styles["content-box"]}>
                    <textarea id='text' className={styles.content}
                              placeholder={placeHolder} onChange={this.onChange}/>
                </form>
                <div className={styles.line}></div>
            </div>
        )
    }
}