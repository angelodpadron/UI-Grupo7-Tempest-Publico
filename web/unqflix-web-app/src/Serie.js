import React, { useState, useEffect } from 'react';
import api from './Api';
import { Link } from 'react-router-dom';

import './Serie.css'
import Nabvar from './Navbar';
import VideoModal from './VideoModal';


export default function Serie(props){

    //consts
    const serieID = props.match.params.id
    const [currentSerie, setCurrentSerie] = useState(undefined)
    const [currentSeason, setCurrentSeason] = useState(undefined)
    const [loadingPage, setLoadingPage] = useState(true)

    //user
    const [currentUser, setCurrentUser] = useState(undefined)

    //button (favorites)
    const [onFavorites, setOnFavorites] = useState(false)
    
    
    
    

    //on render
    useEffect (() => {

        //token header
        let token = {headers: {"Authentication": sessionStorage.getItem('currentUser')}}

        api.get('/user', {headers: {"Authentication": sessionStorage.getItem('currentUser')}})
        .then(response => setCurrentUser(response.data))
        

        if (props.location.state !== undefined) {
            if (props.location.state.userFavorites.length > 0) {
                var result = props.location.state.userFavorites.filter(elem => elem.id === serieID)
                if (result.length > 0) {
                    setOnFavorites(true)
                }
            }
        }

        //find serie
        api.getContentId(serieID, token)
        .then(response => {
            setCurrentSerie(response.data)
            setCurrentSeason(response.data.seasons[0])
            setLoadingPage(false)
            console.log("serie init response", response.data)
            console.log("default season initialize", response.data.seasons[0])
                                
        })
        .catch(e => console.log("series init fail", e.response))

    }, [setCurrentSerie])
    

    

    if (loadingPage){
        return(
            <div>Loading...</div>
        )
    }

    function checkOnFavorites() {
        if (!onFavorites) {
            return (<button className="btn btn-primary btn-lg" onClick={handleUserFavorites}>Add to Favorites</button>)
        } else {
            return (<button className="btn btn-danger btn-lg" onClick={handleUserFavorites}>Remove from Favorites</button>)
        }
    }

    function buildTab(season){
        return(
            <li class="nav-item">
                <button class="btn btn-link" name={season.title} onClick={updateCurrentSeason}>{season.title}</button>                
            </li>
        )
    }   
    
    function buildChapterList(chapter){
        let url = "/player/".concat(chapter.id)
        return( <>                          
                <li class="media">
                    <img class="mr-3" src={chapter.thumbnail} width="300px"/>
                    <div class="media-body">
                        <h5 class="mt-0 mb-1">{chapter.title}</h5>
                        <p>{chapter.description}</p>
                        <p>Duration: {chapter.duration} minutes</p>
                        <p>
                            <VideoModal url={chapter.video}/>
                        </p>                        
                    </div>
                </li>
                <hr></hr>
                </>              

        )
    }

    function buildRelatedContentGrid(content){
        return(
            <div className="col banners">
                <img src={content.poster} alt={content.title}/>
            </div>
        )
        
    }

    function handleUserFavorites(){
        let token = {headers: {'Authentication': sessionStorage.getItem("currentUser")}}
        let payload = {'id': currentSerie.id}
        api.addToUserFavorites(payload, token)
        .then(response => 
            {
                console.log(response.data)
                setOnFavorites(!onFavorites)
            })
    }

    function addToUserViewed(){
        //temporal
        let token = {headers: {'Authentication': sessionStorage.getItem("currentUser")}}
        let payload = {'id': currentSerie.id}
        api.addToUserViewed(payload, token)
        .then(response => console.log(response.data))
    }

    function updateCurrentSeason(event){        
        setCurrentSeason(currentSerie.seasons.find(elem => elem.title === event.target.name))
    }

    return (
                
        <>
        {console.log(currentUser)}
        <Nabvar/>

        <div className="jumbotron jumbotron-fluid">           
            <div class="media">
                <img class="mr-3" src={currentSerie.poster} alt={currentSerie.title}/>
                <div class="media-body">
                    <h5 class="mt-0">{currentSerie.title}</h5>
                    <p>{currentSerie.description}</p>
                    <p>
                        <Link to="#" className='btn btn-primary btn-lg' onClick={addToUserViewed}>Play from the begining</Link>                        
                    </p>
                    <p>
                        {checkOnFavorites()}
                    </p>
                </div>                  
            </div>                   
        </div>
        <div className="container">
            <ul class="nav justify-content-center">               
                {currentSerie.seasons.map(season => buildTab(season))}
            </ul>
            <hr></hr>
            <ul class="list-unstyled">
                {currentSeason.chapters.map(chapter => buildChapterList(chapter))}
            </ul>
        </div>
        <div className="container">            
            <h5>Similar content</h5>
            <hr></hr>
            <div className="row">
                {currentSerie.relatedContent.map(content => buildRelatedContentGrid(content))}
            </div> 
        </div>    
        
        <div>
            
        </div>
        </>
        
    )

}