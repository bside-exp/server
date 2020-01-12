import React, {Component} from 'react'
import Nav from "../Nav";
import styles from './index.module.css'
import Plain from "./plain";
import Pointed from "./pointed";

export default class Term extends Component {

    state = {
        pointed:
            [{
                point: 1,
                content: "회원은 본인회원과 가족회원으로 구분합니다.",
            }, {
                point: 2,
                content: "본인회원이란 이 약관을 승인하고 삼성카드(주)에 카드 발급을 신청하여 카드사로부터 카드를 발급받은 분을 말합니다.",
            }, {
                point: 3,
                content: "본인회원이란 이 약관을 승인하고 삼성카드(주)에 카드 발급을 신청하여 카드사로부터 카드를 발급받은 분을 말합니다.",
            }, {
                point: 4,
                content: "본인회원이란 이 약관을 승인하고 삼성카드(주)에 카드 발급을 신청하여 카드사로부터 카드를 발급받은 분을 말합니다.",
            }, {
                point: 5,
                content: "본인회원이란 이 약관을 승인하고 삼성카드(주)에 카드 발급을 신청하여 카드사로부터 카드를 발급받은 분을 말합니다.",
            }]
    }

    close = () => {
        console.log("close")
        self.close()
    }

    render() {

        return (
            <div className={styles.container}>
                <Nav class={styles.nav} onRight={this.close}> 이용약관 </Nav>
                <div className={styles.content}>
                    <Plain title="제1조 (목적)"
                           content="이 약관은 회원의 권익보호 및 거래관계의 명확화를 위하여 신용카드사(이하 “카드사”라 함)와 신용카드(이하 “카드”라 함)를 이용하고자 하는 회원 간의 필요한 사항을 정함을 목적으로 합니다."/>
                    <Pointed title="제2조 (회원)" content={this.state.pointed}/>
                </div>
            </div>
        )
    }
}

