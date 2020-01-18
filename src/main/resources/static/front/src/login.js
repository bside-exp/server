import React, {Component} from 'react';
import ReactDom from 'react-dom';
import Login from './component/Login'
import styles from './login.module.css'

class App extends Component {
    render() {
        return (
            <div className={styles.container}>
                <Login/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
