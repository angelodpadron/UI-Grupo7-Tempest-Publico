import React from 'react'
import './LoginSignUp.css'


class Login extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            username: '',
            password: ''
        }
    }

    handleChange = (event) => {
        this.setState({[event.target.name]: event.target.value});              
    }
  
    atemptLogin = () => {
        let entryUsername = this.state.username;
        let entryPasswrod = this.state.password;

        if (entryUsername !== user.user && entryPasswrod !== user.password){
            alert("Wrong inputs");
        }
        else{
            alert("Nice");
        }

    }

    render() {
        return(
        <div className= "container">
            <h1>Login</h1>
            <form onSubmit={this.atemptLogin}>
                <input 
                    type='text'
                    name='username'
                    placeholder= 'username...'                
                    onChange={this.handleChange}
                />
                <input 
                    type='password'
                    name='password'
                    placeholder= 'password...'                
                    onChange={this.handleChange}
                />
                <hr></hr>           
                <div className= "loginButtons">
                    <button type='submit' name='submit'>Login</button>
                    <button type="button">Register</button>
                </div>                
            </form>
            <p>If you're new 'round here, hit the Register button to sign up!</p>
            
        </div>
    );
    }

}

//USUARIO TEMPORAL

const user = {user: "user", password: "password"}

export default Login