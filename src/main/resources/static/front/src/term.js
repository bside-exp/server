import React, {Component} from 'react';
import ReactDom from 'react-dom';
import styles from './term.module.css'

class App extends Component{
    render(){
        return (
            <div className={styles.container}>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
