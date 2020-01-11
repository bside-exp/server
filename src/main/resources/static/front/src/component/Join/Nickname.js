import React, {Component, Fragment} from 'react'
import styles from './Nickname.module.css'

export default class Nickname extends Component{

    onChange = (e) => {
        this.props.onChange(e.target.value)
    }

    render() {
        return(
            <Fragment>
                <form className={styles.container}>
                    <input
                        className={this.props.check ? [styles.textInput, styles.duplicate].join(' ') : styles.textInput}
                        type="text"
                        placeholder="닉네임" onChange={this.onChange}/>
                    <div className={styles.error} style={{display: this.props.check ? 'block' : 'none'}}>
                        중복된 닉네임입니다
                    </div>
                </form>
            </Fragment>
        )
    }
}
