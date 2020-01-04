import React, {Component, Fragment} from 'react'
import Nav from "../Nav";
import EamilInput from "./EamilInput";
import Password from "./Password";
import PasswordCheck from "./PasswordCheck";
import Nickname from "./Nickname";
import Term from "./Term";
import RectBtn from "./RectBtn";

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

const emailInput = {
    position: "absolute",
    top: "230px"
}

export default class Join extends Component {

    render() {

        return(
            <div >
                <Nav/>
                <img src="/image/logo.svg" style={logoStyle}/>
                <span style={welcomeStyle}>경험공유에 오신 것을 환영합니다.</span>
                <EamilInput/>
                <Password/>
                <PasswordCheck/>
                <Nickname/>
                <Term/>
                <RectBtn/>
            </div>
        )
    }
};