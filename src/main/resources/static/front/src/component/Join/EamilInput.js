import React, {Component, Fragment} from 'react'
import styles from './EmailInput.module.css'

const containerStyle = {
    float: "left",
    position: "absolute",
    top: "230px",
    left: "20px"
}

const underlineStyle = {
    width: "150px",
    borderLeftWidth: "0px",
    borderRightWidth: "0px",
    borderTopWidth: "0px",
    borderBottomWidth: "1px",
    paddingBottom: "15px",
    fontFamily: "AppleSDGothicNeo",
    fontSize: "16px",
    letterSpacing: "-0.4",
    backgroundColor: "#FFFFFF",
    fontcolor: "#999999",
    borderColor: "#e0e0e1",
    placeholder: "#999999"
}

const arrow = {
    position: "absolute",
    left: "137px",
    bottom: "21px"
}

export default class EamilInput extends Component {

    onNameChange = (e) => {
        this.props.onChange(e.target.value)
    }

    render() {
        return (
            <Fragment>
                <form className={styles.container}>
                    <input type="text" className={styles.textInput}
                           placeholder="이메일" onChange={this.onNameChange}/>
                </form>
            </Fragment>
        )
    }
}