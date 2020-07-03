import React, { useEffect,useState } from 'react'
import Api from './Api';
import { Redirect } from 'react-router-dom';
import './Movie.css'

const Movie = (props) => {
	const [movie, setMovie] = useState(undefined);	
  const [id] = useState(props.match.params.id);
  const [loading, setLoading] = useState(true)
  

  useEffect(() => {
    
      const currentToken = {headers: {"Authentication": sessionStorage.getItem("currentUser")}}

      Api.getContentId(id, currentToken)
      .then(response => {
        setMovie(response.data)
        setLoading(false)})      
  })


  if (loading){
    return(
      <h1>Loading...</h1>
    )
  }

  const poster = (contentData, id) => {
		let url = '/content/'.concat(id);
		return (
      <a href={url}>
        <div className ="relcontent"> 		
			<img className="contentIcon" src={contentData.poster} alt={contentData.title}/>
      </div>
      </a>		
		);
	}
   

return (          
      <div className ="containerMovie">
          <div className ="featuresMovie">
            <p className ="title"><h3>{movie.title}</h3></p>
            <img className ="poster" src={movie.poster}/>
            <p className ="description">{movie.description}</p>
            <button className="reproduceButn"></button>
          </div>
        <p className ="relatedContentTitle"><h4>Related Content</h4></p>
        <div className ="relatedContent">
          {movie.relatedContent.map( cont =>poster(cont,cont.id))}           
        </div>
    </div>
   );  
}

export default Movie;