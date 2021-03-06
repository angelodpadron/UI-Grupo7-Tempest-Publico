import axios from 'axios'

const host = 'http://localhost:7000';

function get(location, token){
    return axios.get(host.concat(location), token)
}

function post(location, payload){
    return axios.post(host.concat(location), payload)
}

function getBanners(token){
    return axios.get(host.concat('/banners'), token)
}

function register(payload){
    return post('/register', payload)
}

function searchAPI(text, token){
    return axios.get(host.concat('/search?text=').concat(text), token)
}

function getContentId(id, token){
    return axios.get(host.concat('/content/'.concat(id)), token)
}

function addToUserFavorites(payload, token){
    return axios.post(host.concat('/user/fav/'), payload, token)
}

function addToUserViewed(payload, token){
    return axios.post(host.concat('/user/lastSeen/'), payload, token)
}

export default {
    get,
    post,
    getBanners,
    register,
    searchAPI,
    getContentId,
    addToUserFavorites,
    addToUserViewed
    }