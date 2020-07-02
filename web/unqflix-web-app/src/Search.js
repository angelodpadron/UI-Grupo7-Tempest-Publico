import React, {useState, useEffect} from 'react';
import api from './Api';
import { useLocation, Link } from "react-router-dom";

function useQuery(){
    return new URLSearchParams(useLocation().search)
}

const SearchPage = () => {
    const[keyWord, setKeyWord] = useState(useQuery().get("text"));    
    const[searchContent, setSearchContent] = useState([]);
    const[banners, setBanners] = useState([]);
    const[loading, setLoading] = useState(true);
    
    

    const payload = {
        params: {
            text: keyWord
        }
    }

    useEffect( () => {   
        //search init
        api.searchAPI(payload)
        .then(response => {
                console.log("response data de search", response.data.content)
                setSearchContent(response.data.content)        
        })
        .catch(e => console.log("error en get search", e))

        //banners init
        api.getBanners().
        then(response => {
            setBanners(response.data.banners)
            setLoading(false)})             
                   
    }, []);

 
    const handleChange = (e) => {
        setKeyWord(e.target.value);
    }

    const canSearch = () => {
        return !(keyWord.length > 0)
    }

    const poster = (id) => {   
        let filtredBanners = banners.find(elem => elem.id === id)
        let url = ('/content/'.concat(id))
        return (
        <div>
            <Link to={url}>
            <img src={filtredBanners.poster} alt={filtredBanners.title}/>
            </Link>
        </div>)
    }

    if (loading){
        return(
			<div class="d-flex justify-content-center">
  				<div className="spinner-border" role="status">
    				<span className="sr-only">Loading...</span>
  				</div>
			</div>
        )
    }else{
        if (searchContent.length === 0){
            return(
                <div>
                    <p>No hubo resultados...</p>
                </div>
            );

        } else{ 
            return(
                <>
                    <div className="topnav">
                        <Link to='/home'>UNQFlix</Link>
                        <div className="search-container">
                            <form onSubmit={useEffect}>
                                <button type="submit" disabled={canSearch()}>Go</button>
                                <input type="text" name="text" onChange={handleChange} placeholder="Search..."/>
                            </form>
                        </div>				
                    </div>
                    <div className="content">
                        <div className="banners">
                            <div className="searchContent">                                                                              
                                {searchContent.map(content => poster(content.id))}
                            </div>
                        </div>
                    </div>
                </>
            );        
        }    
    }


}

export default SearchPage;