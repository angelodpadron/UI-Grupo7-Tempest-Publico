import React, { useEffect,useState } from 'react'
import Api from './Api';
import { Redirect ,Link } from 'react-router-dom';
import Nabvar from './Navbar';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Content.css';
import VideoModal from './VideoModal';



export default function Movie (props)  {
  const movieID = props.match.params.id
	const [movie, setMovie] = useState(undefined);	
  const [id] = useState(props.match.params.id);
  const [loading, setLoading] = useState(true)

  //button (favorites)
  const [onFavorites, setOnFavorites] = useState(false)


  useEffect(() => {      

    const currentToken = {headers: {"Authentication": sessionStorage.getItem("currentUser")}}

    if (props.location.state !== undefined) {
      if (props.location.state.userFavorites.length > 0) {
        var result = props.location.state.userFavorites.filter(elem => elem.id === movieID)
          if (result.length > 0) {
            setOnFavorites(true)
          }
        }
    }

    Api.getContentId(id, currentToken)      
    .then(response => {
        setMovie(response.data)
        setLoading(false)})      
  },  [])


  if (loading){
    return(
      <h1>Loading...</h1>
    )
  }

  function checkOnFavorites() {
    if (!onFavorites) {
        return (<button className="btn btn-primary btn-lg" onClick={handleUserFavorites}>Add to Favorites</button>)
    } else {
        return (<button className="btn btn-danger btn-lg" onClick={handleUserFavorites}>Remove from Favorites</button>)
    }
  }

  function handleUserFavorites(){
    let token = {headers: {'Authentication': sessionStorage.getItem("currentUser")}}
    let payload = {'id': movie.id}
    Api.addToUserFavorites(payload, token)
    .then(response => 
      {
        console.log(response.data)
        setOnFavorites(!onFavorites)
      })
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
        <Nabvar/>          
       
        <div className ="jumbotron jumbotron-fluid">
            <img class="mr-3" src={movie.poster}/>
            <div className="media-body">
              <h1 className ="mt-0">{movie.title} </h1>
              <p >{movie.description}</p>
              <p>
                <VideoModal url={movie.video} id={movieID}/>
              </p>
              <p>
                {checkOnFavorites()}
              </p>
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

