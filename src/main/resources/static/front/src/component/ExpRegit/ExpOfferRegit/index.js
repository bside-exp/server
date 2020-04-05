import React, {Component} from 'react'
import styles from './index.module.css'
import Nav from "./Nav";
import TextInput from "../TextInput";
import ScrollSelector from "../ScrollSelector";
import axios from 'axios'
import ScrollMultiSelector from "../ScrollMultiSelector";
import Tag from "../Tag";
import Title from "../Title";
import MultilineTextInput from "../MultilineTextInput";

export default class ExpOfferRegit extends Component {

    state = {
        title: '',
        industry: '',
        duty: '',
        expOfferTypes: [],
        email: '',
        contact: '',
        firmName: '',
        expDuration: '',
        description: '',
        tags: []
    }

    setTitle = (title) => {
        this.setState({
            ...this.state,
            title: title
        })
    }

    setIndustry = (industry) => {
        this.setState({
            ...this.state,
            industry: industry
        })
    }

    setJob = (job) => {
        this.setState({
            ...this.state,
            duty: job
        })
    }

    setOffer = (offer) => {
        this.setState({
            ...this.state,
            expOfferTypes: offer
        })
    }

    setEmail = (email) => {
        this.setState({
            ...this.state,
            email: email
        })
    }

    setCall = (call) => {
        this.setState({
            ...this.state,
            contact: call
        })
    }

    setFirm = (firm) => {
        this.setState({
            ...this.state,
            firmName: firm
        })
    }

    setDuration = (duration) => {
        this.setState({
            ...this.state,
            expDuration: duration
        })
    }

    setDes = (des) => {
        this.setState({
            ...this.state,
            description: des
        })
    }
    industry = ['기술/IT', '보건/복지 서비스업', '제조업', '금융업', '교육/서비스업', '테스트업', '테테테테테'];
    job = ['전략/기획', '개발', '기타']

    setTags = (tags) => {
        this.setState({
            ...this.state,
            tags: tags
        })
    }

    submit = async () => {
        const token = localStorage.getItem("expAccessToken")
        const headers = {
            "Authorization": "Bearer " + token
        }
        const result = await axios.post("/api/exp-offer", {
            ...this.state
        }, {
            headers: headers
        })

        window.close()
    }

    componentDidMount() {
        this.setState({
            ...this.state,
            industry: this.industry[0],
            duty: this.job[0]
        })
    }

    render() {
        return (
            <div className={styles.container}>
                <Nav submit={this.submit}/>
                <Title label='제목'
                       placeHolder='제공하고 싶은 경험 제목을 입력해 주세요'
                       paddingTop={styles.pd20}
                       func={this.setTitle}
                />
                <ScrollSelector label='산업군'
                                content={this.industry}
                                paddingTop={styles.pd30}
                                func={this.setIndustry}
                />
                <ScrollSelector label='직무'
                                content={this.job}
                                paddingTop={styles.pd30}
                                func={this.setJob}
                />
                <ScrollMultiSelector label='제공 형태'
                                     content={['Email 제공', '유선통화', '오프라인 인터뷰']}
                                     paddingTop={styles.pd30}
                                     func={this.setOffer}
                />
                <TextInput label='이메일'
                           placeHolder='이메일 주소를 입력해주세요'
                           paddingTop={styles.pd30}
                           func={this.setEmail}
                />
                <TextInput label='연락처'
                           placeHolder='연락처 입력시 - 은 제외하고 입력해주세요'
                           paddingTop={styles.pd30}
                           func={this.setCall}
                />
                <TextInput label='회사명'
                           placeHolder='경험을 습득한 회사명을 입력해주세요'
                           paddingTop={styles.pd30}
                           func={this.setFirm}
                />
                <TextInput label='경험기간'
                           placeHolder='개월 단위로 입력해주세요 ex. 15개월'
                           paddingTop={styles.pd30}
                           func={this.setDuration}
                />
                <MultilineTextInput label='자기소개'
                                    placeHolder='자기소개 및 나의 경험을 소개해 주세요'
                                    paddingTop={styles.pd30}
                                    func={this.setDes}
                />
                <Tag func={this.setTags} tags={this.state.tags}/>
            </div>

        )
    }
};