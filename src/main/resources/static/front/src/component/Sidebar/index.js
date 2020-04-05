import React, {Component, Fragment} from 'react'
import styles from './index.module.css'
import Profile from "./Profile";
import {moveToOfferList, moveToOfferRegit, moveToRequestList, moveToRequestRegit} from "../../util/Location";
import {getNewNoticeNum, getNotices, readNotices} from "../../util/Notice";

export default class Sidebar extends Component {

    state = {
        display: false,
        login: false,
        newNotice: 0,
        notices: [],
        showNotice: false
    }

    defaultProps = {
        display: false
    }

    toggle = () => {
        this.props.toggle();
        this.setState({
            ...this.state,
            display: !this.state.display
        })
    }

    toggleNotice = () => {
        if (this.state.newNotice)
            readNotices()
        this.setState({
            ...this.state,
            newNotice: 0,
            showNotice: !this.state.showNotice
        })
    }

    clickSidebar = (e) => {
        e.stopPropagation()
    }

    logout = () => {
        localStorage.removeItem("expAccessToken")
        this.setState({
            login: !this.state.login
        })
    }

    login = () => {
        window.open("/login")
    }

    componentDidMount() {
        getNewNoticeNum().then(data => this.setState({
            ...this.state,
            newNotice: data
        }))

        getNotices().then(data => this.setState({
            ...this.state,
            notices: data
        }))
    }

    render() {

        const token = localStorage.getItem("expAccessToken")

        if (token) {
            var login = (
                <div className={[styles.con24, styles.pointer].join(' ')}>
                    <span className={styles.logout} onClick={this.logout}>로그아웃</span>
                </div>
            )
        } else {
            var login = (
                <div className={[styles.con24, styles.pointer].join(' ')}>
                    <span className={styles.logout} onClick={this.login}>로그인</span>
                </div>
            )
        }

        const style = {
            display: this.props.display ? 'inline' : 'none'
        }

        const showNotice = {
            display: this.state.showNotice ? 'inline' : 'none'
        }

        let newNoticeNum = ''
        if (this.state.newNotice) {
            newNoticeNum = (
                <div className={styles.noti}>
                    <span className={styles.text}>{this.state.newNotice}</span>
                </div>
            )
        }

        let notices = ''
        if (this.state.notices.length) {
            notices = this.state.notices.map(notice => {
                const onClick = () => {
                    if (notice.link)
                        location.href = notice.link
                }
                return (
                    <Fragment>
                        <div className={styles['notice-line']}></div>
                        <div onClick={onClick}>
                            <span>{notice.title}</span>
                            <span>{notice.content}</span>
                        </div>
                        <div className={styles['notice-line']}></div>
                    </Fragment>
                )
            })
        }

        return (
            <div className={styles.container} style={style} onClick={this.toggle}>
                <div className={styles.sidebar} onClick={this.clickSidebar}>
                    <div className={styles.top}/>
                    <Profile/>
                    <div className={styles.line}/>
                    <div id="알림" className={styles.con24} onClick={this.toggleNotice}>
                        <img src="/image/bell.svg" className={styles.icon}/>
                        <span className={styles.text}>알림</span>
                        {newNoticeNum}
                    </div>
                    <div className={styles.notices} style={showNotice}>
                        {notices}
                    </div>
                    <div id="요청" className={styles.con24} onClick={moveToRequestRegit}>
                        <img src="/image/pencel.svg" className={styles.icon}/>
                        <span className={styles.text}>경험 요청서 작성</span>
                    </div>
                    <div id="제공" className={[styles.con24, styles.b].join(' ')} onClick={moveToOfferRegit}>
                        <img src="/image/pencel.svg" className={styles.icon}/>
                        <span className={styles.text}>나의 경험 작성</span>
                    </div>
                    <div className={styles.line}/>
                    <div className={styles.lbox}>
                        <p className={[styles.text, styles.m].join(' ')} onClick={moveToRequestList}>경험 요청 리스트</p>
                        <p className={[styles.text, styles.m].join(' ')} onClick={moveToOfferList}>경험 제공 리스트</p>
                        <p className={styles.text}>태그로 보기</p>
                    </div>
                    <div className={styles.qbox}>
                        <p className={[styles.text, styles.m].join(' ')}>FAQ</p>
                        <p className={[styles.text, styles.m].join(' ')}>공지사항</p>
                        <p className={styles.text}>회사소개</p>
                    </div>
                    <div className={styles.line}/>
                    {login}
                </div>
            </div>
        );
    }
}