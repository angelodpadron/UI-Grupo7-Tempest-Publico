import React, {useState, useEffect} from 'react';
import api from './Api'
import './Home.css'

const Home = () => {
	const [banners, setBanners] = useState([]);
	const [unnautorized, setUnnautorized] = useState(false);		

	useEffect(() => {
		api.getBanners()
		.then(response => setBanners(response.data.banners))
		.catch(() => setUnnautorized(true))
	}, []);	

	if (unnautorized) {
		return (<div>Error al acceder a la API</div>);
	}


	// TODO: mover estos constructores en otras clases
	const poster = (contentData) => {
		return (<img src={contentData.poster} alt={contentData.title}/>);
	}


	//render
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
				<div className="banners-carousel"></div>
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