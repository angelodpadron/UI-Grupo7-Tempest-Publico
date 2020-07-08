import React, { useEffect,useState } from 'react'
import { Link } from 'react-router-dom';
import ReactPlayer from 'react-player'
import ReactJWPlayer from 'react-jw-player'
import './Home.css'
import Api from './Api';

export default function Movie(props){

	const [content, setContent] = useState(undefined);	
  const [id] = useState(props.match.params.id);
  const [loading, setLoading] = useState(true)
  

  useEffect(() => {      

    const currentToken = {headers: {"Authentication": sessionStorage.getItem("currentUser")}}

    Api.getContentId(id, currentToken)      
    .then(response => {
        setContent(response.data)
        setLoading(false)})      
    },[])


    if (loading){
      return(
        <h1>Loading...</h1>
      )
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
          <div className = "reproductor" style={{width:'100%',height:'100%',position:"absolute"} }>
          <ReactJWPlayer
          playerScript=  {content.video}
          />   
             
             {/* <ReactPlayer
                url = {content.video}
                width = '100%'
                height = '90%'
                config={{
                         youtube: {
                         playerVars: {
                                      showinfo:0,
                                      rel:0,
                                     controls:1
                                     }}
                      }}
              /> */}
          </div>
      </>
   )
}

