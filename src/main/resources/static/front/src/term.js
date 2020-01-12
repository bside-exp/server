import React, {Component} from 'react';
import ReactDom from 'react-dom';
import styles from './term.module.css'
import Term from "./component/Term";

class App extends Component{
    render(){
        return (
            <div className={styles.container}>
                <Term/>
            </div>
        )
    }
}

ReactDom.render(<App/>, document.getElementById('root'));
