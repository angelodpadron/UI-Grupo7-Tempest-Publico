import React, {useState, useEffect} from 'react';
import api from './Api'
import './Home.css'
import { useHistory, Link } from 'react-router-dom';
import session from './Session';

//temporal
import axios from 'axios'
import Nabvar from './Navbar';


const Home = () => {
	const [currentUser, setCurrentUser] = useState(undefined);	
	const [banners, setBanners] = useState([]);
	const [searchQuery, setSearchQuery] = useState("");
	const [loading, setLoading] = useState(true);
	const history = useHistory()
	
	useEffect( () => {
		if (!sessionStorage.length > 0){
			history.push('/login')
		}
		
		const currentToken = {headers: {'Authentication': sessionStorage.getItem("currentUser")}}
		
		//users init
		axios.get('http://localhost:7000/user', 
		currentToken)
		.then(response => {
			setCurrentUser(response.data);
			})
		.catch(error => console.log(error))		
		
		//banners init
		api.getBanners(currentToken)
		.then(response => {setBanners(response.data.banners);
			setLoading(false)})				
		
	}, []);	

	// 

	const handleChange = (e) => {
		setSearchQuery(e.target.value)
	}
	
	const performSignOut = () =>{
		session.logoff()
		setCurrentUser(undefined)
		history.push('/login')
	}

	const toSearchPage = () =>{
		let query = '?text='.concat(searchQuery);
		let path = '/search'.concat(query);
		history.push(path);
	}	

	// TODO: mover estos constructores en otras clases
	const poster = (contentData, id) => {
		let url;		
		if (id.includes("mov")){
			url = '/movie/'.concat(id);
		}

		if (id.includes("ser")){
			url = '/serie/'.concat(id)
		}

		return (		
		<Link to={url}>
			<img src={contentData.poster} alt={contentData.title}/>
		</Link>
		
		);
	}

	//favorites
	const favPoster = (id) => {
		console.log(id)
		const foundBanner = banners.find(elem => elem.id === id)
		return (poster(foundBanner, foundBanner.id))
	}

	const canSearch = () => {
		return !(searchQuery.length > 0);
	}

	//render
	if (loading){
		return(
			<div class="d-flex justify-content-center">
  				<div className="spinner-border" role="status">
					<span class="spinner-border spinner-border-sm"></span>
  				</div>				
			</div>
		)
	}else{
		console.log("userData", currentUser)
		return(			
			<div className="wrapper">
				<Nabvar/>
				{/* <nav class="navbar navbar-light bg-light">
  					<Link to='/home' class="navbar-brand">
						<img src={process.env.PUBLIC_URL + '/logo_transparent.png'} width="30" height="30"/>
					</Link>
						<form class="form-inline my-2 my-lg-0" onSubmit={toSearchPage}>
      						<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="text" onChange={handleChange}/>
      						<button class="btn btn-outline-primary my-2 my-sm-0" type="submit" disabled={canSearch()}>Search</button>
    					</form>
						<button class="btn btn-danger my-2 my-sm-0" type="submit" onClick={performSignOut}>Sign out</button>						
				</nav> */}
							
				
				<div className="container">
					<h1>VIEWED</h1>
					<div className="banners">						
						{currentUser.lastSeen.map(content => favPoster(content.id))}
					</div>
					<hr></hr>
					<h1>FAVORITES</h1>
					<div className="banners">						
						{currentUser.favorites.map(content => favPoster(content.id))}
					</div>
					<hr></hr>
					<h1>EXPLORE</h1>
					<div className="banners">						
						{banners.map(content => poster(content, content.id))}
					</div>				
				</div>
					
				{/* <div className="bottomvar">
					<a href="#">Climb up</a>	
				</div>		 */}
			</div>
			
		);	
	}

}
	


export default Home;