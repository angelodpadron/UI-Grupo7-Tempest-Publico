import React, {useState} from 'react';
import { useHistory, Link } from 'react-router-dom';
import Session from './Session';

function Nabvar(){
    
    const [searchQuery, setSearchQuery] = useState("");
    const history = useHistory()

    const handleChange = (e) => {
		setSearchQuery(e.target.value)
	}
	
	const performSignOut = () =>{
		Session.logoff()
		// setCurrentUser(undefined)
		history.push('/login')
	}

	const toSearchPage = () =>{
		let query = '?text='.concat(searchQuery);
		let path = '/search'.concat(query);
		history.push(path);
    }
    
    const canSearch = () => {
		return !(searchQuery.length > 0);
    }

    return(
        <nav class="navbar navbar-light bg-light">
            <Link to='/home' class="navbar-brand">
                <img src={process.env.PUBLIC_URL + '/logo_transparent.png'} width="30" height="30"/>
            </Link>
                <form class="form-inline my-2 my-lg-0" onSubmit={toSearchPage}>
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="text" onChange={handleChange}/>
                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit" disabled={canSearch()}>Search</button>
                </form>
                <button class="btn btn-danger my-2 my-sm-0" type="submit" onClick={performSignOut}>Sign out</button>						
        </nav>
    )

}

export default Nabvar;