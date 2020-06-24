import React from 'react';

class Home extends React.Component{
	constructor(){
		super();
	}

	render(){
		return(
		<div className="container">
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
				<div className="banners-carousel"></div>
				<div className="viewed">Recently watched</div>
				<div className="favourites">Favourites</div>
			</div>
		</div>
	)	
	}
	
}

export default Home;