import React, { useState, useEffect } from 'react'
import './LoginSignUp.css'
import { Link, Redirect } from 'react-router-dom';
import login from './Session'


import './Login.css'


export default function Login(){
    
    const [state, setState] = useState({
        email: '',
        password: ''             
    })

    const [redirect, setRedirect] = useState(false)

    const [errorLogin, setErrorLogin] = useState(false)

    useEffect(() => {
        if (sessionStorage.length > 0){
            setRedirect(true)
        }
    })

    const handleChange = (e) => {
        const {id , value} = e.target   
        setState(oldState => ({
            ...oldState,
            [id] : value
        }))
    }
    
    
   
    const atemptLogin = () => {
        let payload = {
            email: state.email,
            password: state.password
        }

        login.login(payload)
        .then(response => {
            let sessionToken;
            sessionToken = response.headers.authentication;
            console.log("session token", sessionToken);
            sessionStorage.setItem('currentUser', sessionToken);
            setRedirect(true);               
        })
        .catch(e => {
            console.log(e.response)
            setErrorLogin(true)
        })       
            
    }

    const canLogin = () => {
        //basico... muy basico
        return !(state.email.length > 0 && state.password.length > 0);
    }    
    
    if (redirect){
         return <Redirect to='/home'/>        
    }
    

    return(
    <div className="container">        
        <div className="row">
            <div className="col-sm">
                <img className="login-logo" src={process.env.PUBLIC_URL + '/logo_transparent.png'}/>
            </div>
            <div className="col-sm"/>
       
            <div className="col-sm login-forms" >
                <h1>Login</h1>
                <hr></hr>                
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" onChange={handleChange}/>
                    <small id="emailHelp" class="form-text text-muted">Don't forget the @</small>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" onChange={handleChange}/>
                </div>           
                <button onClick={atemptLogin} disabled={canLogin()} className='btn btn-primary'>Login</button>
                <Link to="/signup" className="btn btn-light">Sign up</Link>
                <hr></hr>
                <p>If you're new 'round here, hit the Register button to sign up!</p>
                {errorLogin &&
                    <div class="alert alert-danger" role="alert">
                    incorrect email or password! 
                    </div>
                }

            </div>
        </div>
    </div>
        
    
    );
    
    
}