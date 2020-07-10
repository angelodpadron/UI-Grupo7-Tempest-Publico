import React from 'react'
import './LoginSignUp.css'
import api from './Api';
import { Redirect, Link } from 'react-router-dom';



class Register extends React.Component{
    constructor (props){
        super(props);
        this.state = {
            email: '',
            name: '',
            password: '',
            imageURL: '',
            cardNumber: '',
            redirectToLogin: false,
            errorRegister: false
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
        .then(response => {
            console.log(response)
            this.setState({redirectToLogin: true})
        })
        .catch((error) => {
            console.log(error.response)
            this.setState({errorRegister: true})
            });
    }

    canAtemptRegister = () => {
        return !(Object.values(this.state).every((value) => value !== ''));
    }   
    
    
    render() {   
        
        if (this.state.redirectToLogin){
            return(
                <Redirect to={{
                    pathname: '/login',
                    state: { successRegister: true }
                }}
        />
            )
        }

        return(                            
            
            <div className="container">
                <div className="row">
                    <div className="col-sm">
                        <img className="login-logo" src={process.env.PUBLIC_URL + '/logo_transparent.png'}/>
                    </div>
                    <div className="col-sm">
                        <h1>Sign up on UNQFlix</h1>
                        <hr></hr>
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" id="name" name="name" aria-describedby="emailHelp" onChange={this.handleChange}/>                                
                        </div>
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" onChange={this.handleChange}/>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password" onChange={this.handleChange}/>
                        </div>
                        <div class="form-group">
                            <label for="imageURL">Image source</label>
                            <input type="url" class="form-control" id="imageURL" name="imageURL" onChange={this.handleChange}/>
                        </div>
                        <div class="form-group">
                            <label for="cardNumber">Credit card number</label>
                            <input type="cardNumber" class="form-control" id="cardNumber" name="cardNumber" aria-describedby="emailHelp" onChange={this.handleChange}/>
                            <small class="form-text text-muted">This app connection isn't encrypted, a middle man could sneak!</small>                                
                        </div>
                        <div className="registerButtons">
                            <button className='btn btn-primary' disabled={this.canAtemptRegister()} onClick={this.atemptRegister}>Register</button>
                            <button type="reset" className='btn btn-light'>Clear</button>
                            
                            {this.state.errorRegister &&
                            <>
                            <hr></hr>
                            <div class="alert alert-danger" role="alert">
                            The entered email address is already in use
                            </div>
                            </>
                        }
                        </div>
                            <hr></hr>
                            <p><Link to='/login'>Log in</Link> if you are registred already</p>
                        </div>                        
            </div>
            
                
                
                
                
            </div>

    );
    }
}

export default Register;