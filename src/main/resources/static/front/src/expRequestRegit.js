import React, {Component} from 'react';
import ReactDom from 'react-dom';
import styles from './container.css'
import ExpRequestRegit from "./component/ExpRegit/ExpRequestRegit";

class App extends Component {
    render() {
        return (
            <div className={styles.container}>
                <ExpRequestRegit/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
