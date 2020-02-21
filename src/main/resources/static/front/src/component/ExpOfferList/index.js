import React, {Component} from 'react'
import styles from './index.css'
import Nav from "./Nav";
import Footer from "../Footer";
import axios from 'axios'
import ListCard from "../ListCard";

export default class ExpOfferList extends Component {

    state = {
        page: 1,
        offers: []
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

    componentDidMount() {
        this.call()
    }

    render() {

        const offers = this.state.offers.map((offer) => {

            let types = ''
            offer.expOfferTypes.forEach((type) => {
                types = types + "・" + type.name
            })

            const bottom = offer.firmName + "・" + offer.expDuration + "개월 근무" + types

            return (
                <ListCard style={styles.offers} title={offer.title} content={offer.description}
                          bottom={bottom}/>
            )
        })

        return (
            <div className={styles.container}>
                <div className={styles['top-block']}></div>
                <Nav/>
                <div className={styles.list}>
                    {offers}
                </div>
                <Footer/>
            </div>
        )
    }
};