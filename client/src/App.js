import React, {Component} from 'react';
import AppNav from './AppNav';
import {Row} from 'reactstrap'

import grailsLogo from './images/grails-cupsonly-logo-white.svg';
import reactLogo from './images/logo.svg';
import {CLIENT_VERSION, REACT_VERSION, SERVER_URL} from './config';
import 'whatwg-fetch';
import Footer from "./Footer";
import PhraseList from "./PhraseList";

class App extends Component {

  constructor() {
    super();

    this.state = {
      serverInfo: {},
      clientInfo: {
        version: CLIENT_VERSION,
        react: REACT_VERSION
      },
      collapse: false
    }
  }

  toggle = () => {
    this.setState({collapse: !!this.state.collapse})
  };

  componentDidMount() {
    fetch(SERVER_URL + '/application')
      .then(r => r.json())
      .then(json => this.setState({serverInfo: json}))
      .catch(error => console.error('Error connecting to server: ' + error));

  }

  render() {
    const {serverInfo, clientInfo, collapse} = this.state;

    return [
      <AppNav serverInfo={serverInfo} clientInfo={clientInfo} collapse={collapse} toggle={this.toggle} key={0}/>,
      <div className="grails-logo-container" key={1}>
        <img className="grails-logo" src={grailsLogo} alt="Grails"/>
        <span className="plus-logo">+</span>
        <img className="hero-logo" src={reactLogo} alt="React"/>
      </div>,

      <Row key={2}>
        <div id="content">
          <section className="row colset-2-its">
            <h1 style={{textAlign: 'center'}}>Grails+React <a href="https://en.wikipedia.org/wiki/Diceware" target="_blank">Diceware </a>phrase generator</h1>
            <br/>
            <div className="container-fluid">
                <ul className="list-group"><PhraseList/></ul>
              </div>
            <br/>
            <div id="controllers" role="navigation">
                {process.env.REACT_APP_SERVER_URL}
              <h2>Available Controllers (env: {process.env.NODE_ENV}):</h2>
              <ul>
                {serverInfo.controllers ? serverInfo.controllers.map(controller => {
                  return <li key={controller.name}><a
                    href={SERVER_URL + controller.logicalPropertyName}>{controller.name}</a>
                  </li>;
                }) : null}
              </ul>
            </div>
          </section>

        </div>

      </Row>,
      <Footer key={3}/>
    ];
  }
}

export default App;
