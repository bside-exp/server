import React, {Component} from 'react'
import styles from './Tag.css'

export default class Tag extends Component {

    state = {
        editing: false
    }

    toggle = () => {
        const next = !this.state.editing;
        this.setState({
            editing: next
        })
    }

    enter = (e) => {
        if (e.key === 'Enter') {
            this.setState({
                editing: false
            })
            const value = e.target.value;
            if (value) {
                const next = this.props.tags.concat(e.target.value);
                this.props.func(next)
            }
        }
    }

    delete = (e) => {
        const parent = e.target.parentNode
        let next = this.props.tags
        parent.childNodes.forEach((child) => {
            if (child.id === 'tag') {
                next = next.filter(tag => tag !== child.innerText)
            }
        })
        this.props.func(next)
    }

    render() {

        const tags = this.props.tags.map((tag, index) => {
            return (
                <div index={index} className={styles.tag}>
                    <div className={styles["text-box"]}>
                        <span>#</span>
                        <span id="tag">{tag}</span>
                        <img className={styles.img} onClick={this.delete} src="/image/x.svg"/>
                    </div>
                </div>
            )
        })

        let input = ''
        if (this.state.editing) {
            input = (
                <div className={styles["input-box"]} onKeyPress={this.enter}>
                    <div className={styles.wrap}>
                        <span>#</span>
                        <input className={styles.input} type="textarea"/>
                    </div>
                </div>
            )
        }

        let toggleBtn = ''
        if (this.props.tags.length < 10) {
            toggleBtn = (
                <div className={styles["tag-btn"]} onClick={this.toggle}>
                    <span className={styles.text}>#태그추가</span>
                </div>
            )
        }

        return (
            <div className={styles.container}>
                <div className={styles["title-box"]}>
                    <span className={styles.title}>태그 추가</span>
                    <span className={styles.limit}>최대 10개</span>
                </div>
                <div className={styles["tag-box"]}>
                    {input}
                    {tags}
                    {toggleBtn}
                </div>
            </div>
        )
    }
}