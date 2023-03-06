import React, {Component} from 'react';
import {MapTo} from '@adobe/aem-react-editable-components';

export default class Signup extends Component {

constructor(props) {
    super(props);
    this.state = {value: '',fruitValue:'',message:'',stateValue:'',cities:[],cityValue:''};
   
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.selectFruit = this.selectFruit.bind(this);
    this.letsClick=this.letsClick.bind(this);
    this.selectState=this.selectState.bind(this);
    this.selectCity=this.selectCity.bind(this);
  }
   selectFruit(event){debugger;
   console.log("fruit selection is changed");
  this.setState({fruitValue: event.target.value});
  }
  selectState(event){
  this.setState({stateValue:event.target.value});
  if(event.target.value == "Jharkhand"){
    this.setState({cities:["jamshedpur","Ranchi","Chandil","Hazaribagh"]});
  }
  if(event.target.value == "Bihar"){
    this.setState({cities:["Patna","sasaram","Nalanda","Gaya"]});
  }
  
  }
  selectCity(e){
    console.log(e.target.value);
  }

  handleChange(event) {debugger;
     console.log("name is changing");

    this.setState({value: event.target.value});
  }

  letsClick(event){
    event.preventDefault();
    this.setState({message : "Form is submitted"});
  }

  handleSubmit(event) {
    alert('A name was submitted: ' + this.state.value);
    event.preventDefault();
  }


  render() {
    const fruit=[{seb:'apple'},{seb:'strawberry'},{seb:'coconut'},{seb:'Mango'}];
    const states=["Jharkhand","Bihar","Andhra","Jammu"];


          return (
              <div className="signup">
               <form>
                 <label>
                   Name:
                             </label>
                  <input type="text" value={this.state.value} onChange={this.handleChange} />
                  <label>
                     Pick your favorite flavor:
                                      </label>

                 <select value={this.state.fruitValue} onChange={this.selectFruit}>
                 {fruit.map((fru) =>(
        <option key={fru.seb} value={fru.seb}>{fru.seb}</option>
      ) )};
                 </select>

                 <select value={this.state.stateValue} onChange={this.selectState}>
                 {states.map((stat)=>(<option key={stat} value={stat}>{stat}</option>)
                 )}
                 </select>
                 <select value={this.state.cityValue} onChange={this.selectCity}>
                 {this.state.cities.map((city)=>(<option key={city} value={city}>{city}</option>)
                 )}
                 </select>
                    <button type="submit" onClick={this.letsClick}>Submit</button>
                 <div>
                  {this.state.message}
                 </div>
               </form>
              </div>
          );
      }
}