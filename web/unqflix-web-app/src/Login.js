import React, { useState } from 'react'
import './LoginSignUp.css'
import { Link } from 'react-router-dom';
import api from './Api';


export default function Login(props){
    
    const [state, setState] = useState({
        email: '',
        password: ''
    })

    const handleChange = (e) => {
        const {id , value} = e.target   
        setState(oldState => ({
            ...oldState,
            [id] : value
        }))
        console.log(state.email)
    }           
   
    const atemptLogin = () => {
        let payload = {
            email: state.email,
            password: state.password
        }

        api.post('/login', payload)
            .then((response) => {
            console.log(response)})
            .catch((error) => console.log(error));
    }
    
          
    return(
    <div className= "container">
        <h1>Login</h1>
            <form onSubmit={atemptLogin}>
                <input type="email" 
                    className="" 
                    id="email" 
                    placeholder="email..." 
                    value={state.email}
                    onChange={handleChange}
                />      
                <input type="password" 
                    className="" 
                    id="password" 
                    placeholder="password..."
                    value={state.password}
                    onChange={handleChange} 
                />
            <hr></hr>           
                <div className= "loginButtons">
                    <button onClick={atemptLogin} className='btn btn-light'>Login</button>
                    <Link to="/signup" className="btn btn-light">Sign up</Link>
                </div>                
            </form>
        <p>If you're new 'round here, hit the Register button to sign up!</p>            
    </div>
    );
    
    
}