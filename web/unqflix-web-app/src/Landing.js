import React from 'react';
import { Link } from 'react-router-dom';

import './Landing.css'

export default function Landing(){
    return(
        <div className= "container">
            <h1>Welcome to UNQFlix</h1>
            <Link to="/login" className='btn btn-light'>Log In</Link>
            <Link to="/home" className='btn btn-light'>Home (dev-mode)</Link>
        </div>
    );
}

