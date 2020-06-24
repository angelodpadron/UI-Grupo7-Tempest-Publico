import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';

//BOOTSTRAP
import 'bootstrap/dist/css/bootstrap.min.css';

//LOGIN
import Login from './Login.js';
//REGISTER
import Register from './Register.js'
//HOME (linux build)
import Home from './Home.js'

const enConstruccion =  
  (<div className = "placeHolder">
    <p>En construccion...</p>
    <img src= "https://i.pinimg.com/474x/de/77/2d/de772d3bfb6620692c83edcf2561eb41.jpg" />
  </div>);  


ReactDOM.render(<React.StrictMode>
  <Home />
</React.StrictMode>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
