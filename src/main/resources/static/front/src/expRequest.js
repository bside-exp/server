import React, {Component} from 'react';
import ReactDom from 'react-dom';
import styles from './container.css'
import ExpRequest from "./component/ExpRequest";

class App extends Component {
    render() {
        return (
            <div className={styles.container}>
                <ExpRequest/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
