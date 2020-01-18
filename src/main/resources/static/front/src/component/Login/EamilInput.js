import React, {Component, Fragment} from 'react'
import styles from './EmailInput.module.css'


export default class EamilInput extends Component {
    input = null

    onNameChange = (e) => {
        this.props.onChange(e)
        this.setState({
            email: e.target.value
        })
    }


    render() {
        return (
            <Fragment>
                <div className={styles.container}>
                    <form className={styles.form}>
                        <input type="text"
                               className={styles.textInput}
                               placeholder="이메일" onChange={this.onNameChange}/>
                    </form>
                </div>
            </Fragment>
        )
    }
}