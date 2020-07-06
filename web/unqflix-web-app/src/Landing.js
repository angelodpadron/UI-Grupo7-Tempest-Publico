import React from 'react';
import { Link } from 'react-router-dom';

import './Landing.css'

export default function Landing(){
    return(
        <div className= "jumbotron">
            <h1 className="display-4">Welcome to UNQFlix</h1>
            <hr className="my-4"/>
            <Link to="/login" className='btn btn-primary btn-lg'>Begin!</Link>
        </div>
    );
}

