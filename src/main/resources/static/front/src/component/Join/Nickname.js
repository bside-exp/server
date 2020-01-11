import React, {Component, Fragment} from 'react'
import styles from './Nickname.module.css'

export default class Nickname extends Component{

    input = null
    state = {
        nickname: ''
    }

    onChange = (e) => {
        this.props.onChange(e.target.value)
        this.setState({
            nickname: e.target.value
        })
    }

    onDelete = () => {
        this.props.onChange('')
        this.setState({
            nickname: ''
        })
        this.input.value = ''
    }

    render() {
        return(
            <Fragment>
                <form className={styles.container}>
                    <input
                        className={this.props.check ? [styles.textInput, styles.duplicate].join(' ') : styles.textInput}
                        type="text" ref = {ref => {this.input = ref}}
                        placeholder="닉네임" onChange={this.onChange}/>
                    <img src='/image/x2.svg' className={styles.x} onClick={this.onDelete} style={{display: this.state.nickname.length == 0 ? 'none' : 'block'}}/>
                    <div className={styles.error} style={{display: this.props.check ? 'block' : 'none'}}>
                        중복된 닉네임입니다
                    </div>
                </form>
            </Fragment>
        )
    }
}
