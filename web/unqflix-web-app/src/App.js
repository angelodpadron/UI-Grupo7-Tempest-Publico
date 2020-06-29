import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link, Redirect } from 'react-router-dom';

import Landing from './Landing'
import Home from './Home';
import Login from './Login';
import Register from './Register';

export default class App extends React.Component{
    
    
    render(){
        return(
            <Router>
                <Route exact path= '/' component={Landing}/>
                <Route exact path= '/login' component={Login}/>
                <Route exact path= '/signup' component={Register}/>
                <Route exact path= '/home' component={Home}/>
            </Router>
        );
    }
}

