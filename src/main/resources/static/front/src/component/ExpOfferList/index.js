import React, {Component} from 'react'
import styles from './index.css'
import Footer from "../Footer";
import axios from 'axios'
import ListCard from "../ListCard";
import Sidebar from "../Sidebar";
import NavBack from "../NavBack";

export default class ExpOfferList extends Component {

    state = {
        page: 1,
        offers: [],
        sidebar: false
    }

    call = async () => {
        const url = '/api/exp-offer?&page=' + this.state.page
        const offers = (await axios.get(url)).data.content

        const next = this.state.offers.concat(offers)
        this.setState({
            ...this.state,
            offers: next,
            page: this.state.page + 1
        })
    }
    toggleSidebar = () => {
        this.setState({
            ...this.state,
            sidebar: !this.state.sidebar
        })
    }

    scroll = () => {
        let scrollHeight = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight)
        let scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop)
        let clientHeight = document.documentElement.clientHeight

        if (scrollTop + clientHeight === scrollHeight) {
            this.call()
        }
    }

    componentDidMount() {
        this.call()
        window.addEventListener('scroll', this.scroll, true)
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

            const bottom = offer.firmName + "・" + offer.expDuration + "개월 근무" + types

            return (
                <ListCard style={styles.offers} title={offer.title} content={offer.description}
                          bottom={bottom} onClick={call}/>
            )
        })

        return (
            <div className={styles.container}>
                <Sidebar toggle={this.toggleSidebar} display={this.state.sidebar}/>
                <div className={styles['top-block']}></div>
                <NavBack style={styles.nav} right={this.toggleSidebar}>경험 제공</NavBack>
                <div className={styles.list}>
                    {offers}
                </div>
                <Footer/>
            </div>
        )
    }
};