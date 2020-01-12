import React, {Component, Fragment} from 'react'

const navStyle = {
    height: "44px",
    left: "0px",
    width: "100%",
    position: "absolute",
    top: "44px",
    boxSizing: "border-box"
}

const combinedShapeStyle = {
    width: "20px",
    height: "15.5px",
    objectFit: "contain",
    borderRadius: "0.8px",
    position: "absolute",
    right: "16px",
    top: "16px"
}

export default class Nav extends Component {

    render() {
        return (
            <Fragment>
                <nav className="navbar" style={navStyle}>
                    <img className="Combined-Shape" style={combinedShapeStyle} src="/image/x2.svg"/>
                </nav>
            </Fragment>
        )
    }
}
