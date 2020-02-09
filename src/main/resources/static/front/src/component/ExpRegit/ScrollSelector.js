import React, {Component} from 'react'
import styles from './ScrollSelector.css'

export default class ScrollSelector extends Component {

    state = {
        selected: 0
    }

    onItemClick = (e) => {
        const s = Number(e.target.getAttribute('index'));
        this.setState({
            ...this.state,
            selected: s
        })
        this.props.func(this.props.content[s])
    }

    render() {

        const label = this.props.label;
        const content = this.props.content;
        const paddingTop = this.props.paddingTop ? this.props.paddingTop : '';

        const con = content.map((content, index) => {
            let style = index === 0 ? [styles.item, styles["first-item"]].join(' ') : styles.item;
            if (this.state.selected === index) {
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