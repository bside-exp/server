import React, {Component, Fragment} from 'react'
import styles from './EmailInput.module.css'


export default class EamilInput extends Component {

    onNameChange = (e) => {
        this.props.onChange(e.target.value)
    }

    render() {
        return (
            <Fragment>
                <form className={styles.container}>
                    <input type="text"
                           className={this.props.check ? [styles.textInput, styles.duplicate].join(' ') : styles.textInput}
                           placeholder="이메일" onChange={this.onNameChange}/>
                    <div className={styles.error} style={{display: this.props.check ? 'block' : 'none'}}>
                        이미 사용하고 있는 이메일 입니다
                    </div>
                </form>
            </Fragment>
        )
    }
}