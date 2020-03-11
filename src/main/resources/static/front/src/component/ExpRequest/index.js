import React, {Component, Fragment} from 'react'
import styles from './index.css'
import axios from 'axios'
import NavBack from "../NavBack";
import Sidebar from "../Sidebar";
import {getDecodedToken} from "../../util/User";
import Footer from "../Footer";
import {convertOfferTypeString} from "../../util/CommonUtil";

export default class ExpRequest extends Component {
    state = {
        data: '',
        sidebar: false
    }

    call = async (id) => {
        const url = `/api/exp-request/${id}`
        const item = await axios.get(url)
        this.setState({
            ...this.state,
            data: item.data
        })

        console.log(item)
    }

    componentDidMount() {
        const id = window.location.pathname.split('/').pop()
        this.call(id)
    }

    toggleSidebar = () => {
        this.setState({
            ...this.state,
            sidebar: !this.state.sidebar
        })
    }

    render() {

        const date = this.state.data.createdDate
        const user = this.state.data.user

        const dateCrop = date ? date.split("T")[0] : ''
        const nickname = user ? user.nickName + ' ' : ''
        const userId = user ? user.id : ''
        const tags = this.state.data.tags ? this.state.data.tags : []
        const types = this.state.data.expOfferTypes ? this.state.data.expOfferTypes : []
        const industry = this.state.data.industry ? this.state.data.industry.name : ''
        const duty = this.state.data.duty ? this.state.data.duty.name : ''

        let tagString = ''
        for (let tag in tags) {
            tagString = tagString + ' #' + tags[tag].name
        }
        let offerTypeString = ''
        for (let type in types) {
            if (type != 0) {
                offerTypeString + "・"
            }
            offerTypeString = offerTypeString + convertOfferTypeString(types[type].name)
        }

        const token = getDecodedToken()


        let button = ''
        if (userId === parseInt(token.sub)) {
            button = (
                <div className={styles['btn-box']}>
                    <div className={styles['btn-dlt']}>
                        <div className={styles['btn-tbl']}>
                            <div className={styles['btn-text']}>삭제</div>
                        </div>
                    </div>
                    <div className={styles['btn-mdf']}>
                        <div className={styles['btn-tbl']}>
                            <div className={styles['btn-text']}>수정</div>
                        </div>
                    </div>
                </div>
            )
        } else {
            button = (
                <div className={styles['btn-box']}>
                    <div className={styles['btn-req']}>
                        <div className={styles['btn-tbl']}>
                            <div className={styles['btn-text']}>경험 요청하기</div>
                        </div>
                    </div>
                </div>
            )
        }

        return (
            <Fragment>
                <Sidebar toggle={this.toggleSidebar} display={this.state.sidebar}/>
                <div className={styles.container}>
                    <div className={styles['top-block']}></div>
                    <NavBack style={styles.nav} right={this.toggleSidebar}>모두의 경험</NavBack>
                    <div className={styles['top-box']}>
                        <div className={styles.title}>
                            {this.state.data.description}
                        </div>
                        <div className={styles.middle}>{industry}・{duty}・{offerTypeString}</div>
                        <div className={styles.bottom}>
                            <span>{nickname}</span>
                            <span>{dateCrop}</span>
                        </div>
                    </div>
                    {button}
                    <div className={styles['main-box']}>
                        {this.state.data.description}
                    </div>
                    <div className={styles.tags}>{tagString}</div>
                    <div className={styles.line}></div>
                    <Footer/>
                </div>
            </Fragment>
        )
    }
}