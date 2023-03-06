import { Page, withModel } from '@adobe/aem-react-editable-components';
import React from 'react';
import Header from './components/Header/Header';
import OpenWeather from './components/OpenWeather/OpenWeather';
import SignUp from './components/Signup/Signup';
import AboutUs from './components/AboutUs/AboutUs';


// This component is the application entry point
class App extends Page {
  render() {
    return (
      <div>
      <Header />

        {this.childComponents}
        {this.childPages}
        <div className="container">
         <AboutUs/>
        </div>
        <div className="container">
 <SignUp/>
 </div>
      </div>

    );
  }
}

export default withModel(App);
