import React, {Component} from 'react';
import ReactDom from 'react-dom';
import Join from "./component/Join";

const style = {
	width: "100%"
}

class App extends Component{
	render(){
		return (
			<div style={style}>
                <Join/>
			</div>
		)
	}
}

ReactDom.render(<App/>, document.getElementById('root'));
