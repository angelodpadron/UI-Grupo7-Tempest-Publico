import React, { useState } from 'react'
import ModalVideo from 'react-modal-video'

import './VideoModal.scss'

export default function VideoModal({url}){    
    
    const [isOpen, setIsOpen] = useState(false)
    var youtubeId = url.split('=')[1]

    function openModal(){
        setIsOpen(true)
    }
    

    return (
        <div>
            <ModalVideo channel='youtube' isOpen={isOpen} videoId={youtubeId} onClose={() => setIsOpen(false)} />
            <button className="btn btn-success" onClick={openModal}>Play</button>
        </div>
    )
}