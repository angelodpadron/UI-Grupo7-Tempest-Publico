import React, {useState, useEffect} from 'react';
import api from './Api'
import './Home.css'
import { useHistory, Link } from 'react-router-dom';

//temporal
import axios from 'axios'


const Home = () => {
	const [currentUser, setCurrentUser] = useState(undefined);	
	const [banners, setBanners] = useState([]);
	const [searchQuery, setSearchQuery] = useState("");
	const [loading, setLoading] = useState(true);
	const history = useHistory()
	
	useEffect(() => {
		if (!sessionStorage.length > 0){
			history.push('/login')
		}
				
		//banners init
		api.getBanners()
		.then(response => {setBanners(response.data.banners);
		})		
		
		//users init
		axios.get('http://localhost:7000/user', 
		{headers: {'Authentication': sessionStorage.getItem("currentUser")}})
		.then(response => {
			setCurrentUser(response.data);
			setLoading(false)})
		.catch(error => console.log(error))		
	}, []);	

	// 

	const handleChange = (e) => {
		setSearchQuery(e.target.value)
	} 

	const toSearchPage = () =>{
		let query = '?text='.concat(searchQuery);
		let path = '/search'.concat(query);
		history.push(path);
	}	

	// TODO: mover estos constructores en otras clases
	const poster = (contentData, id) => {
		let url = '/content/'.concat(id);
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
    				<span className="sr-only">Loading...</span>
  				</div>
			</div>
		)
	}else{
		console.log("userData", currentUser)
		return(			
			<div className="wrapper">
				
				<div className="topnav">
					<Link to='/home'>UNQFlix</Link>					
					<div className="user-dropdown">
						<button class="dropbtn">
							{currentUser.name}
							<i class="fa fa-caret-down"></i>
						</button>
						{/* <div class="dropdown-content">
							<a href="#">Link 1</a>
							<a href="#">Link 2</a>
							<a href="#">Link 3</a>
    					</div> */}
					</div>						
					<div className="search-container">
						<form onSubmit={toSearchPage}>
							<button type="submit" disabled={canSearch()}>Go</button>
							<input type="text" name="text" onChange={handleChange} placeholder="Search..."/>
						</form>
					</div>
																	
				</div>
				<hr></hr>
				<div className="content">
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
					
				<div className="bottomvar">
					<a href="#">Climb up</a>	
				</div>		
			</div>
			
		);	
	}

}
	


export default Home;