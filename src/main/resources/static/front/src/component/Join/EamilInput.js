import React, {Component, Fragment} from 'react'
import styles from './EmailInput.module.css'


export default class EamilInput extends Component {
    input = null
    state = {
        email: ''
    }

    onNameChange = (e) => {
        this.props.onChange(e.target.value)
        this.setState({
            email: e.target.value
        })
    }

    onDelete = () => {
        this.props.onChange('')
        this.input.value = ''
        this.setState({
            email: ''
        })
    }

    render() {
        return (
            <Fragment>
                <form className={styles.container}>
                    <input type="text" ref={ref => {this.input = ref}}
                           className={this.props.check ? [styles.textInput, styles.duplicate].join(' ') : styles.textInput}
                           placeholder="이메일" onChange={this.onNameChange}/>
                           <img src="/image/x2.svg" className={styles.x}
                                onClick={this.onDelete} style={{display: this.state.email.length == 0 ? 'none' : 'block'}}/>
                    <div className={styles.error} style={{display: this.props.check ? 'block' : 'none'}}>
                        이미 사용하고 있는 이메일 입니다
                    </div>
                </form>
            </Fragment>
        )
    }
}