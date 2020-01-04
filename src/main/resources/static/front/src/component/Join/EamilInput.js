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

const underlineSelector = {
    position: "relative",
    borderLeftWidth: "0px",
    borderRightWidth: "0px",
    borderTopWidth: "0px",
    borderBottomWidth: "1px",
    borderBottom: "1px solid #e0e0e1",
    paddingBottom: "15px",
    backgroundColor: "#FFFFFF",
    borderColor: "#e0e0e1",
    display: "inline-block",
    width: "150px",
}

const textInSelector = {
    fontFamily: "AppleSDGothicNeo",
    fontSize: "16px",
    letterSpacing: "-0.4",
    color: "#999999"
}

const atStyle = {
    paddingRight: "11px",
    paddingLeft: "10px"
}

const arrow = {
    position: "absolute",
    left: "137px",
    bottom: "21px"
}

export default class EamilInput extends Component {

    render() {
        console.log(styles.input)

        return (
            <Fragment>
                <form style={containerStyle}>
                    <input type="text" className={styles.textInput} placeholder="이메일" style={underlineStyle}/>
                    <span style={atStyle}>@</span>
                    <div style={underlineSelector}>
                        <span style={textInSelector}>이메일 선택</span>
                        <img src="/image/down-arrow.svg" style={arrow}/>
                    </div>
                </form>
            </Fragment>
        )
    }
}