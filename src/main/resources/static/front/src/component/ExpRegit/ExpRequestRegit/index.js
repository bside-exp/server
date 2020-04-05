import React, {Component} from 'react'
import styles from './index.css'
import Nav from "./Nav";
import Tag from "../Tag";
import ScrollSelector from "../ScrollSelector";
import ScrollMultiSelector from "../ScrollMultiSelector";
import Title from "../Title";
import MultilineTextInput from "../MultilineTextInput";
import axios from "axios";

export default class ExpRequestRegit extends Component {

    state = {
        title: '',
        industry: '',
        duty: '',
        types: [],
        description: '',
        tags: []
    }
    industry = ['기술/IT', '보건/복지 서비스업', '제조업', '금융업', '교육/서비스업', '테스트업', '테테테테테'];
    job = ['전략/기획', '개발', '기타']

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
            types: offer
        })
    }

    setDes = (des) => {
        this.setState({
            ...this.state,
            description: des
        })
    }

    setTag = (tags) => {
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
        const result = await axios.post("/api/exp-request", {
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
                       placeHolder='요청하시고 싶은 경험 제목을 입력해 주세요'
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
                <MultilineTextInput label='설명'
                                    placeHolder='어떤 경험이 필요한지 설명해 주세요'
                                    paddingTop={styles.pd30}
                                    func={this.setDes}
                />
                <Tag func={this.setTag} tags={this.state.tags}/>
            </div>
        )
    }
}