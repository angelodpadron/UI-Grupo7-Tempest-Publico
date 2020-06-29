import React from 'react'
import './LoginSignUp.css'

class Register extends React.Component{
    constructor (props){
        super(props);
        this.state = {
            email: '',
            name: '',
            password: '',
            imageURL: '',
            cardNumber: ''
        }
    }

    handleChange = (event) => {
        this.setState({[event.target.name]: event.target.value});              
    }

    atemptRegister = () => {
        //alguna logica rara aca...
    }

    render() {
        return(                            
            <div className="container">
                <h1>Sign up on UNQFlix</h1>
                <form onSubmit={this.atemptRegister}>
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
                        type='url'
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
                        <button type='submit' className='btn btn-light'>Register</button>
                        <button type="reset" className='btn btn-light'>Clear</button>
                    </div>
                </form>   
                <p>By creating an account you agree to our Terms and Privacy.</p>             
            </div>
            
    );
    }
}

export default Register;