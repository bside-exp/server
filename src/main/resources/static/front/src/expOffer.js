import React, {Component} from 'react';
import ReactDom from 'react-dom';
import styles from './container.css'
import ExpOffer from "./component/ExpOffer";

class App extends Component {
    render() {
        return (
            <div className={styles.container}>
                <ExpOffer/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
