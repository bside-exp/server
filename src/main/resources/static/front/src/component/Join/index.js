import React, {Component} from 'react'
import Nav from "../Nav";
import EamilInput from "./EamilInput";
import Password from "./Password";
import PasswordCheck from "./PasswordCheck";
import Nickname from "./Nickname";
import Term from "./Term";
import RectBtn from "./RectBtn";
import styles from './index.module.css'
import axios from 'axios'

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

    termAgree = () => {
        const agree = !this.state.termAgree
        this.setState({
            ...this.state,
            termAgree: agree
        })
    }

    submit = () => {
        axios.post("/api/auth/user/duplicate/email", this.state.email)
            .then(response => {
                this.setState({
                    ...this.state,
                    emailDuplicate: response.data
                })

                axios.post("/api/auth/user/duplicate/nickname", this.state.nickname)
                    .then(response => {
                        this.setState({
                            ...this.state,
                            nicknameDuplicate: response.data
                        })
                        if (!this.state.emailDuplicate && !this.state.nicknameDuplicate) {
                            axios.post("/api/auth/join",
                                {
                                    username: this.state.email,
                                    nickname: this.state.nickname,
                                    password: this.state.password
                                }).then(response => {
                                console.log(response)
                            })
                        }
                    })
            })


    }

    render() {

        return(
            <div className={styles.container}>
                <Nav/>
                <img src="/image/logo.svg" style={logoStyle}/>
                <span style={welcomeStyle}>경험공유에 오신 것을 환영합니다.</span>
                <EamilInput onChange={this.emailChange} check={this.state.emailDuplicate}/>
                <Password onChange={this.passwordChange} conform={this.state.isPasswordConform}/>
                <PasswordCheck onChange={this.passwordCheckChange} same={this.state.isPasswordCheckSame}/>
                <Nickname onChange={this.nicknameChange} check={this.state.nicknameDuplicate}/>
                <Term onClick={this.termAgree} agree={this.state.termAgree}/>
                <RectBtn onClick={this.submit} state={this.state}/>
            </div>
        )
    }
};