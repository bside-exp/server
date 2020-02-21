import React, {Component} from 'react';
import ReactDom from 'react-dom';
import styles from './container.css'
import ExpRequestList from "./component/ExpRequestList";

class App extends Component {
    render() {
        return (
            <div className={styles.container}>
                <ExpRequestList/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
