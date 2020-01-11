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
                    <input className={styles.textInput} type="text"
                           placeholder="닉네임" onChange={this.onChange}/>
                </form>
            </Fragment>
        )
    }
}
