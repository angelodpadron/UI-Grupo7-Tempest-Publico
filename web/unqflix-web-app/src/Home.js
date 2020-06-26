import React, {useState, useEffect} from 'react';
import axios from 'axios';

const Home = () => {
	const [banners, setBanners] = useState(undefined);
	const [unnautorized, setUnnautorized] = useState(false);

	//localhost
	const host = 'http://localhost:7000'		

	useEffect(() => {
		axios.get(host.concat('/banners'))
		.then(response => setBanners(response.data))
		.catch(() => setUnnautorized(true))		
		

	}, []);	

	if (unnautorized) {
		return (
			<div>
				Error al acceder a la API
			</div>
		);
	}

	return(
		<>
			<div className="topnav">
				<a href="#">UNQFlix</a>
				<div className="search-container">
					<form>
						<input type="text" placeholder="Search..."/>
						<button type="submit">Go</button>
					</form>
				</div>
			</div>
			<div className="content">
				<div className="banners-carousel">Carousel placeholder</div>
				<div className="viewed">Recently watched</div>
				<div className="favourites">Favourites</div>
				<div className="banners">Resto de contenidos</div>				
			</div>			
		</>
		
	);	
}
	


export default Home;