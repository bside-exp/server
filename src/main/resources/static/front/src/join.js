import React, {Component} from 'react';
import ReactDom from 'react-dom';
import Join from "./component/Join";
import styles from './join.module.css'

class App extends Component{
	render(){
		return (
			<div className={styles.container}>
                <Join/>
			</div>
		)
	}
}

ReactDom.render(<App/>, document.getElementById('root'));
