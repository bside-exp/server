import React, {Component} from 'react'
import styles from './index.css'
import ListCard from "../ListCard";
import Sidebar from "../Sidebar";
import axios from 'axios'
import Footer from "../Footer";

export default class Main extends Component {

    state = {
        sidebar: false,
        offers: [],
        requests: [],
        page: 1
    }

    toggleSidebar = () => {
        this.setState({
            ...this.state,
            sidebar: !this.state.sidebar
        })
    }

    callOffer = async () => {
        const url = '/api/exp-offer?size=3&page=' + this.state.page
        const offers = (await axios.get(url)).data.content

        const next = this.state.offers.concat(offers)
        this.setState({
            ...this.state,
            offers: next,
            page: this.state.page + 1
        })
    }

    callRequest = async () => {
        const url = 'api/exp-request?size=3&page=1'
        const requests = (await axios.get(url)).data.content

        const next = this.state.requests.concat(requests)
        this.setState({
            ...this.state,
            requests: next
        })
    }


    componentDidMount() {
        this.callOffer()
        this.callRequest()
    }

    moveToOffers = () => {
        location.href = '/exp_offers'
    }

    moveToRequests = () => {
        location.href = '/exp_requests'
    }

    moveToSearch = () => {
        location.href = '/search'
    }

    render() {

        const offers = this.state.offers.map((offer) => {

            let types = ''
            offer.expOfferTypes.forEach((type) => {
                types = types + "・" + type.name
            })

            const call = () => {
                location.href = `/exp_offers/${offer.id}`
            }

            const bottom = offer.firmName + "・" + offer.expDuration + "개월" + types

            return (
                <ListCard style={styles.offers} title={offer.title} content={offer.description}
                          bottom={bottom} onClick={call}/>
            )
        })

        const requests = this.state.requests.map((request, index) => {

            const top = request.industry.name + '・' + request.duty.name

            const bg = 'bg' + (index + 1)

            const call = () => {
                location.href = `/exp_requests/${request.id}`
            }

            return (
                <div className={[styles['request-card'], styles[bg]].join(' ')} onClick={call}>
                    <div className={styles.meta}>{top}</div>
                    <div className={styles.title}>{request.title}</div>
                </div>
            )
        })

        return (
            <div className={styles.container}>
                <Sidebar display={this.state.sidebar} toggle={this.toggleSidebar}/>
                <div className={styles['top-box']}>
                    <img src="/image/combined-shape-w.svg" className={styles.sidebar} onClick={this.toggleSidebar}/>
                </div>
                <div className={styles.search} onClick={this.moveToSearch}>
                    <span className={styles.text}>어떤 분야의 경험을 찾고 계신가요?</span>
                    <img className={styles.img} src='/image/search.svg'/>
                </div>
                <div className={styles['offer-box']}>
                    <div className={styles.top}>
                        <span className={styles.title}>경험 제공</span>
                        <div className={styles.more} onClick={this.moveToOffers}>
                            <span>더보기</span>
                            <img src='image/right-arrow.svg'/>
                        </div>
                    </div>
                    {offers}
                    <div className={styles['more-offer-box']}>
                        <div className={styles['more-offer']} onClick={this.moveToOffers}>
                            <span className={styles.text}>경험제공 더보기</span>
                            <img className={styles.text} src='image/right-arrow.svg'/>
                        </div>
                    </div>
                </div>
                <div className={styles['request-box']}>
                    <div className={styles.top}>
                        <span className={styles.title}>경험 요청</span>
                        <div className={styles.more} onClick={this.moveToRequests}>
                            <span>더보기</span>
                            <img src='image/right-arrow.svg'/>
                        </div>
                    </div>
                    <div className={styles.requests}>
                        {requests}
                    </div>
                </div>
                <Footer/>
            </div>
        )
    }
}