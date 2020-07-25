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
			<Link to={
				{
					pathname:url,
					state:{userFavorites:currentUser.favorites}
				}
				}>
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
				
				<div className="container">
					
					{currentUser.lastSeen.length > 0 &&
					<>
					<h1>VIEWED</h1>
					<div className="banners">						
						{currentUser.lastSeen.map(content => favPoster(content.id))}
					</div>
					<hr/>
					</>
					}
					
					{currentUser.favorites.length > 0 && 
					<>
					<h1>FAVORITES</h1>
					<div className="banners">						
						{currentUser.favorites.map(content => favPoster(content.id))}
					</div>
					<hr></hr>
					</>
					}
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