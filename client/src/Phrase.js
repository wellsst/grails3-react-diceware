import React, {Component} from 'react'

class Phrase extends Component {

    constructor() {
        super();
    }

    render() {
        const {text} = this.props;

        return <li className="list-group-item">{text}</li>
    }
}

export default Phrase;