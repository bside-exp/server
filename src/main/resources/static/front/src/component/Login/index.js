import React, {Component} from 'react';
import Nav from '../Nav';
import styles from './index.module.css';
import EamilInput from "./EamilInput";
import Password from "./Password";
import RectBtn from "./RectBtn";
import Additive from "./Additive";
import axios from 'axios'

export default class Login extends Component {

    state = {
        email: '',
        password: ''
    }

    onEmailChange = (e) => {
        this.setState({
            ...this.state,
            email: e.target.value
        })
    }

    onPasswordChange = (e) => {
        this.setState({
            ...this.state,
            password: e.target.value
        })
    }

    submit = async () => {
        const token = await axios.post("/api/auth/login",
            {
                username: this.state.email,
                password: this.state.password
            })

        localStorage.setItem("expAccessToken", token.data.accessToken)
    }

    render() {

        return (
            <div className={styles.container}>
                <Nav class={styles.nav}/>
                <img src="/image/logo.svg" className={styles.logo}/>
                <span className={styles.welcome}>경험공유에 오신 것을 환영합니다.</span>
                <EamilInput onChange={this.onEmailChange}/>
                <Password onChange={this.onPasswordChange}/>
                <RectBtn state={this.state} onClick={this.submit}/>
                <Additive/>
            </div>
        )
    }
}

