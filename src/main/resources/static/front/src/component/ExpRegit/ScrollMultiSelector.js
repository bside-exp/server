import React, {Component} from 'react'
import styles from './ScrollMultiSelector.css'

export default class ScrollMultiSelector extends Component {

    state = {
        selected: [false, false, false]
    }

    onItemClick = (e) => {
        const s = Number(e.target.getAttribute('index'));
        const selected = this.state.selected.map((item, index) => {
            if (index == s) {
                return !item
            } else {
                return item
            }
        })
        this.setState({
            ...this.state,
            selected: selected
        })

        const result = new Array();
        selected.forEach((item, index) => {
            if (item) {
                result.push(this.props.content[index])
            }
        })

        this.props.func(result)
    }

    render() {
        const label = this.props.label;
        const content = this.props.content;
        const paddingTop = this.props.paddingTop ? this.props.paddingTop : '';

        const con = content.map((content, index) => {
            let style = index === 0 ? [styles.item, styles["first-item"]].join(' ') : styles.item;
            if (this.state.selected[index]) {
                style = [style, styles.selected].join(' ')
            }
            return (
                <li index={index} key={index} className={style} onClick={this.onItemClick}>{content}</li>
            )
        })

        return (
            <div className={[styles.container, paddingTop].join(' ')}>
                <span className={styles.title}>{label}</span>
                <ul className={styles.items}>
                    {con}
                </ul>
                <div className={styles.line}></div>
            </div>
        )
    }
}