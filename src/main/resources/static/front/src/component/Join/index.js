import React, {Component, Fragment} from 'react'
import Nav from "../Nav";
import EamilInput from "./EamilInput";
import Password from "./Password";
import PasswordCheck from "./PasswordCheck";
import Nickname from "./Nickname";
import Term from "./Term";
import RectBtn from "./RectBtn";
import styles from './index.module.css'

const logoStyle = {
    width: "37px",
    height: "28px",
    objectFit: "contain",
    position: "absolute",
    top: "114px",
    right: "169px"
}

const welcomeStyle = {
    width: "256px",
    height: "28px",
    fontFamily: "AppleSDGothicNeo",
    fontSize: "20px",
    fontWeight: "normal",
    fontStretch: "normal",
    fontStyle: "normal",
    lineHeight: "1.4",
    letterSpacing: "-0.4px",
    color: "#4a65f6",
    position: "absolute",
    top : "152px",
    left: "60px",
    whiteSpace: "nowrap"
}

export default class Join extends Component {

    state = {
        email: "",
        password: "",
        passwordCheck: "",
        nickname: "",
        termAgree: false,
        checked: false,
        isPasswordCheckSame: true,
        isPasswordConform: true,
        emailDuplicate: false,
        nicknameDuplicate: false
    }

    emailChange = (email) => {
        this.setState({
            ...this.state,
            email: email
        })
    }

    passwordChange = (password) => {
        const isSame = this.state.passwordCheck == password || this.state.passwordCheck.length == 0
        const pattern = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,10}$/
        const conform = password.length == 0 || pattern.test(password)
        this.setState({
            ...this.state,
            password: password,
            isPasswordCheckSame: isSame,
            isPasswordConform: conform
        })
    }

    passwordCheckChange = (passwordCheck) => {
        const isSame = passwordCheck.length == 0 || this.state.password == passwordCheck
        this.setState({
            ...this.state,
            passwordCheck: passwordCheck,
            isPasswordCheckSame: isSame
        })
    }

    nicknameChange = (nickname) => {
        this.setState({
            ...this.state,
            nickname: nickname
        })
    }

    render() {

        return(
            <div className={styles.container}>
                <Nav/>
                <img src="/image/logo.svg" style={logoStyle}/>
                <span style={welcomeStyle}>경험공유에 오신 것을 환영합니다.</span>
                <EamilInput onChange={this.emailChange}/>
                <Password onChange={this.passwordChange} conform={this.state.isPasswordConform}/>
                <PasswordCheck onChange={this.passwordCheckChange} same={this.state.isPasswordCheckSame}/>
                <Nickname onChange={this.nicknameChange}/>
                <Term/>
                <RectBtn/>
            </div>
        )
    }
};