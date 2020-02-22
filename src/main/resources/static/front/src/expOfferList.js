import React, {Component} from 'react';
import ReactDom from 'react-dom';
import styles from './container.css'
import ExpOfferList from "./component/ExpOfferList";

class App extends Component {
    render() {
        return (
            <div className={styles.container}>
                <ExpOfferList/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
