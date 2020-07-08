import React, { useEffect,useState } from 'react'
import Api from './Api';
import { Redirect ,Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Content.css';


export default function Movie (props)  {
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
		let url;		
		if (id.includes("mov")){
			url = '/movie/'.concat(id);
		}

		if (id.includes("ser")){
			url = '/serie/'.concat(id)
    }
		return (
      <a href={url}>
        <div className ="relcontent"> 		
        <img className="contentIcon" src={contentData.poster} alt={contentData.title}/>
      </div>
      </a>		
		);
	}
   

return (
        <>            
        <div className="topnav">
            <Link to='/home'>UNQFlix</Link>					
            <div className="user-dropdown">
                <button class="dropbtn"> Sign out</button>						
            </div>						
            <div className="search-container">
                <form >
                    <button type="submit">Go</button>
                    <input type="text" name="text" placeholder="Search..."/>
                </form>
            </div>
                                                            
        </div>
        <div className ="jumbotron jumbotron-fluid">
            <img  src={movie.poster}/>
            <div className="container">
              <h1 className ="display-4">{movie.title} </h1>
              <p className ="lead">{movie.description}</p>
              <Link to={`/videoplayer/${movie.id}`}className="btn btn-primary"> <img src={process.env.PUBLIC_URL + '/image/boton-de-reproduccion.svg'} className="reproduceBtnIcon" width="250px" /> Play </Link>
            </div>
        </div>
        <div className="container">
          <p className ="relatedContentTitle"><h4>Related Content</h4></p>
          <div className ="relatedContent">
              {movie.relatedContent.map( cont =>poster(cont,cont.id))}           
          </div>
        </div>
        </>
      );  
}

