import React, {Component} from 'react';
import Counter from '../Counter/Counter';
require('./Header.css');

export default class Header extends Component {

    render() {
        return (
                <header className="Header">
                    <div className="Header-container">
                        <h1>WKND</h1>
                        <Counter/>
                       
                    </div>
                </header>
        );
    }
}