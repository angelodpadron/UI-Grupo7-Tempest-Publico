import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link, Redirect } from 'react-router-dom';

import Landing from './Landing'
import Home from './Home';
import Login from './Login';
import Register from './Register';
import Search from './Search'
import Movie from './Movie.js'
import Serie from './Serie.js'
import VideoPlayer from'./VideoPlayer.js'

import NotFoundPage from './NotFoundPage'

export default class App extends React.Component{

    render(){
        return(
            
            <Router>
                <Switch>            
                    <Route exact path= '/' component={Landing}/>
                    <Route exact path= '/login' component={Login}/>
                    <Route exact path= '/signup' component={Register}/>
                    <Route exact path= '/home' component={Home}/>
                    <Route exact path= '/search' component={Search}/>
                    <Route exact path= '/movie/:id' component= {Movie}/>
                    <Route exact path= '/serie/:id' component= {Serie}/>
                    <Route exact path= '/videoplayer/:id' component= {VideoPlayer}/>
                    
                    <Route path='*'  component={NotFoundPage}/>
                </Switch>
            </Router>
        );
    }
}

