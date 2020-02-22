import React, {Component} from 'react'
import styles from './index.css'
import Nav from "./Nav";
import Footer from "../Footer";
import axios from 'axios'
import ListCard from "../ListCard";
import Sidebar from "../Sidebar";
import scroll from '../../util/Scroll'

export default class ExpRequestList extends Component {

    state = {
        page: 1,
        requests: [],
        sidebar: false
    }

    call = async () => {
        const url = '/api/exp-request?&page=' + this.state.page
        const requests = (await axios.get(url)).data.content

        const next = this.state.requests.concat(requests)
        this.setState({
            ...this.state,
            requests: next,
            page: this.state.page + 1
        })
    }

    toggleSidebar = () => {
        this.setState({
            ...this.state,
            sidebar: !this.state.sidebar
        })
    }

    scrolls = () => {
        scroll(this.call)
    }

    componentDidMount() {
        this.call()
        window.addEventListener('scroll', this.scrolls, true)
    }

    render() {

        const requests = this.state.requests.map((requests) => {

            let types = ''
            requests.expOfferTypes.forEach((type) => {
                types = types + "・" + type.name
            })

            const bottom = requests.industry.name + "・" + requests.duty.name + types

            const call = () => {
                location.href = `/exp_requests/${requests.id}`
            }

            return (
                <ListCard style={styles.offers} title={requests.title} content={requests.description}
                          bottom={bottom} onClick={call}/>
            )
        })
        return (
            <div className={styles.container}>
                <Sidebar toggle={this.toggleSidebar} display={this.state.sidebar}/>
                <div className={styles['top-block']}></div>
                <Nav sidebar={this.toggleSidebar}/>
                <div className={styles.list}>
                    {requests}
                </div>
                <Footer/>
            </div>
        )
    }
};