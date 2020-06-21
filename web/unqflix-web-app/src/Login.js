import React from 'react'
import './Login.css'

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
        console.log(this.state.username);
        console.log(this.state.password);        
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
        <div className= "loginForm">
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
                <input type='submit' name='submit' value='Login'/>    
            </form>
            <div className= "registerRedirect">
                <button type="button">Register</button>
            </div>
        </div>
    );
    }

}

//USUARIO TEMPORAL

const user = {user: "user", password: "password"}

export default Login