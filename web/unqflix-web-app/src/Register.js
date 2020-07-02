import React, { useState } from 'react'
import './LoginSignUp.css'
import api from './Api';

class Register extends React.Component{
    constructor (props){
        super(props);
        this.state = {
            email: '',
            name: '',
            password: '',
            imageURL: '',
            cardNumber: '',
        }
    }

    handleChange = (event) => {
        this.setState({[event.target.name]: event.target.value});              
    }
    
    atemptRegister = () => {
        let payload = {
            name: this.state.name,
            email: this.state.email,
            password: this.state.password,
            creditCard: this.state.cardNumber,
            image: this.state.imageURL

        }

        api.register(payload)
        .then((response) => {console.log(response)})
        .catch((error) => {console.log(error.response)});
    }

    canAtemptRegister = () => {
        return !(Object.values(this.state).every((value) => value !== ''));
    }    

    render() {
        return(                            
            <div className="container">
                <h1>Sign up on UNQFlix</h1>
                
                    <input
                        type='email'
                        name='email'
                        placeholder='email...'
                        onChange={this.handleChange}
                        autoFocus
                        required
                    />
                    <input
                        type='text'
                        name='name'
                        placeholder='name...'
                        onChange={this.handleChange}
                        required
                    />
                    <input
                        type='password'
                        name='password'
                        placeholder='password...'
                        onChange={this.handleChange}
                        required
                    />
                    <input
                        type='text'
                        name='imageURL'
                        placeholder='image link...'
                        onChange={this.handleChange}
                        required
                    />
                    <input
                        type='text'
                        name='cardNumber'
                        placeholder='credit card number...'
                        onChange={this.handleChange}
                        required
                    />
                    <hr></hr>
                    <div className="registerButtons">
                        <button className='btn btn-light' disabled={this.canAtemptRegister()} onClick={this.atemptRegister}>Register</button>
                        <button type="reset" className='btn btn-light'>Clear</button>
                    </div>
                  
                <p>By creating an account you agree to our Terms and Privacy.</p>                                        
            </div>
            
    );
    }
}

export default Register;