import React, {useState, useEffect} from 'react';
import axios from 'axios';

import './Home.css'

const Home = () => {
	const [banners, setBanners] = useState([]);
	const [unnautorized, setUnnautorized] = useState(false);

	//localhost
	const host = 'http://localhost:7000'		

	useEffect(() => {
		axios.get(host.concat('/banners'))
		.then(response => setBanners(response.data.banners))
		.catch(() => setUnnautorized(true))		
		

	}, []);
	
	console.log(banners)

	if (unnautorized) {
		return (
			<div>
				Error al acceder a la API
			</div>
		);
	}

	const poster = (contentData) => {
		return (
			<img src={contentData.poster} alt={contentData.title}/>
		)
	}

	return(
		<>
			<div className="topnav">
				<a href="#">UNQFlix</a>
				<div className="search-container">
					<form>
						<button type="submit">Go</button>
						<input type="text" placeholder="Search..."/>
					</form>
				</div>
			</div>
			<div className="content">
				<div className="banners-carousel">Carousel placeholder</div>
				<div className="viewed">Recently watched</div>
				<div className="favourites">Favourites</div>
				<div className="banners">
					{banners.map(content => poster(content))}
				</div>				
			</div>	
			<div className="bottomvar">
				<a href="#">Climb up</a>	
			</div>		
		</>
		
	);	
}
	


export default Home;