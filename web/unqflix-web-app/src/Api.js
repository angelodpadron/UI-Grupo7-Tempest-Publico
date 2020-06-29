import axios from 'axios'

const api = 'http://localhost:7000';

function get(location){
    return axios.get(api.concat(location))
}

function post(location, payload){
    return axios.post(api.concat(location), payload)
}

function getBanners(){
    return axios.get(api.concat('/banners'))
}

export default {
    get,
    post,
    getBanners}