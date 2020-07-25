import React, { useState } from 'react'
import ModalVideo from 'react-modal-video'

import Api from './Api'

import './VideoModal.scss'

export default function VideoModal({url, id}){    
    
    const [isOpen, setIsOpen] = useState(false)
    var youtubeId = url.split('=')[1]

    function openModal(){
        setIsOpen(true)

        let token = {headers: {'Authentication': sessionStorage.getItem("currentUser")}}
        let payload = {'id': id}
        Api.addToUserViewed(payload, token)
        .then(response => console.log("added to viewed", response.data))
        .catch(e => console.log("error adding to viewed", e.response))     

    }
    

    return (
        <div>
            <ModalVideo channel='youtube' isOpen={isOpen} videoId={youtubeId} onClose={() => setIsOpen(false)} />
            <button className="btn btn-success" onClick={openModal}>Play</button>
        </div>
    )
}