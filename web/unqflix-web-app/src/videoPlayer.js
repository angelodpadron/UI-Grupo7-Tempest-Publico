import React from 'react'
import ReactPlayer from 'react-player'
import './Home.css'

class VideoPlayer extends React.Component{

    constructor(props){
        super(props);
    
        this.state = {
          url:""
        }
      }
    
 render( ){
     return (
       

        <div className = "reproductor" style={{width:'100%',height:'100%',position:"absolute"} }>
            <ReactPlayer
                url = "https://www.youtube.com/watch?v=fBNpSRtfIUA"
                width = '100%'
                height = '90%'
                config={{
                    youtube: {
                      playerVars: {
                                    showinfo:0,
                                    rel:0,
                                    controls:1
                                  }
                             }
                      }
                }
            />
        </div>
     )
 }

}

export default VideoPlayer;