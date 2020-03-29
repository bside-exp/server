import React, {Component, Fragment} from 'react'
import styles from './index.css'
import axios from 'axios'
import {getAccessToken} from "../../../util/User";
import {moveToRequestRegit} from "../../../util/Location";

export default class LinkPopup extends Component {

    state = {
        list: [],
        selected: ''
    }

    toggle = () => {
        this.props.toggle()
    }

    clickSidebar = (e) => {
        e.stopPropagation()
    }

    submit = async () => {
        const token = getAccessToken()
        const url = '/link/request'

        const headers = {
            "Authorization": "Bearer " + token
        }
        const offerId = this.props.offerId
        const requestId = this.state.list[this.state.selected].id

        const data = {
            expOfferId: offerId,
            expRequestId: requestId
        }
        await axios.post(url, data, {
            headers: headers
        })
        this.props.onFinish()
        this.toggle()
    }

    call = async () => {
        const token = getAccessToken()
        const url = '/api/exp-request/user'
        const headers = {
            "Authorization": "Bearer " + token
        }
        const requests = (await axios.get(url, {
            headers: headers
        })).data

        this.setState({
            ...this.state,
            list: requests
        })
    }

    componentDidMount() {
        this.call()
    }

    setSelected = (index) => {
        this.setState({
            ...this.state,
            selected: index
        })
    }

    render() {
        const style = {
            display: this.props.display ? 'inline' : 'none'
        }

        const requests = this.state.list.map((request, index) => {

            let img = '/image/check-def.svg'
            if (index === this.state.selected) {
                img = '/image/check-on.svg'
            }

            const call = () => {
                this.setSelected(index)
            }

            return (
                <Fragment>
                    <div className={styles.request} onClick={call}>
                        <img src={img}/>
                        <span>{request.title}</span>
                    </div>
                    <div className={styles['gray-line']}></div>
                </Fragment>
            );
        })

        const nickname = this.props.provider ? this.props.provider.nickName : ''

        return (
            <div className={styles.container} style={style} onClick={this.toggle}>
                <div className={styles.popup} onClick={this.clickSidebar}>
                    <div className={styles.content}>
                        <div className={styles.top}>
                            <div><span>{nickname}</span>님에게</div>
                            <div>경험 요청을 하시겠습니까?</div>
                        </div>
                        <div className={styles['black-line']}></div>
                        <div className={styles.list}>
                            <div className={styles.add} onClick={moveToRequestRegit}>
                                <img src='/image/plus.svg'/>
                                <span>경험 요청서 작성하기</span>
                            </div>
                            <div className={styles['gray-line']}></div>
                            {requests}
                        </div>
                        <div className={styles['btn-box']}>
                            <div className={styles.cancel} onClick={this.toggle}>
                                <div className={styles.table}>
                                    <div className={styles.cell}>
                                        취소
                                    </div>
                                </div>
                            </div>
                            <div className={styles.submit} onClick={this.submit}>
                                <div className={styles.table}>
                                    <div className={styles.cell}>
                                        경험 요청하기
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }
}