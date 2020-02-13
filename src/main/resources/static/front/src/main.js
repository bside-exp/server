import React, {Component} from 'react';
import ReactDom from 'react-dom';
import styles from './container.css'
import Main from "./component/Main";

class App extends Component {
    render() {
        return (
            <div className={styles.container}>
                <Main/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
