import axios from 'axios'

const host = 'http://localhost:7000';
const loginURL = host.concat('/login');

function login(payload){    
    return axios.post(loginURL, payload)  
}

function logoff(){
    sessionStorage.removeItem("currentUser")
}


export default {
    login,
    logoff    
}

