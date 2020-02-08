import React, {Component} from 'react';
import ReactDom from 'react-dom';
import ExpOfferRegit from "./component/ExpOfferRegit";
import styles from './container.css'

class App extends Component {
    render() {
        return (
            <div className={styles.container}>
                <ExpOfferRegit/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
