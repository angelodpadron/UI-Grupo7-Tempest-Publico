import React, { useEffect,useState } from 'react'
import Api from './Api';
import { Redirect } from 'react-router-dom';
import './Movie.css'



// const movie = {
//   title: "The Godfather",
//   description: "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
//   poster: "https://image.tmdb.org/t/p/w500/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg",
//   video: "https://www.youtube.com/watch?v=fBNpSRtfIUA",
//   relatedContent: [
//     {
//         id: "mov_10",
//         description: "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.",
//         title: "Pulp Fiction",
//         state: true,
//         poster: "https://image.tmdb.org/t/p/w500/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg"
//     },
//     {
//         id: "ser_5",
//         description: "Rebelde is a Mexican telenovela produced by Televisa and created by Cris Morena. It is a remake of a famous Argentine series Rebelde Way adapted for the Mexican audience therefore leading to differences in characters' backgrounds. The series ran for three seasons, the final episode airing in Mexico on June 2, 2006. Rebelde was replaced in June 2006 with Televisa's new series Código Postal.\n\nThe series is set at the Elite Way School, a prestigious private boarding high school in Mexico City with a major plot line revolving around a group of students forming a pop band. Additional subplots involve the school's faculty and the students' parents. One trademark of the show is the random use of English words and phrases, often used by fresa characters.\n\nA notable aspect of the series is that the actors playing the bandmembers are themselves in an actual band named RBD, and perform most of the music used on the show. They have been extremely successful in their own right, becoming one of the most popular acts in Latin America and touring internationally.\n\nRebelde began airing March 21, 2005 and ended on December 15, 2006. The show was also transmitted in 65 other countries, including Serbia, Peru, Romania on Acasa TV, Brazil, Spain on Antena 3, Slovenia, Bulgaria and from September 2009 in Slovakia on TV Doma, in Croatia on Nova TV and in Albania on Vizion Plus.",
//         title: "Rebelde",
//         state: false,
//         poster: "https://image.tmdb.org/t/p/w500/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg"
//     },
//     {
//         id: "ser_3",
//         description: "When three working class kids enroll in the most exclusive school in Spain, the clash between the wealthy and the poor students leads to tragedy.",
//         title: "Elite",
//         state: true,
//         poster: "https://image.tmdb.org/t/p/w500/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg"
//     },
//     {
//         id: "ser_24",
//         description: "A stand-up comedian and his three offbeat friends weather the pitfalls and payoffs of life in New York City in the '90s. It's a show about nothing.",
//         title: "Seinfeld",
//         state: true,
//         poster: "https://image.tmdb.org/t/p/w500/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg"
//     },
//     {
//         id: "mov_4",
//         description: "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
//         title: "Schindler's List",
//         state: true,
//         poster: "https://image.tmdb.org/t/p/w500/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg"
//     }
// ]

// }


const Movie = (props) => {
	const [movie, setMovie] = useState(undefined);	
  const [id] = useState(props.match.params.id);
  const [loading, setLoading] = useState(true)
  

  useEffect(() => {      

      Api.getContentId(id)
      .then(respose => {
        setMovie(respose.data)
        setLoading(false)})      
  })


  if (loading){
    return(
      <h1>Loading...</h1>
    )
  }

  const poster = (contentData, id) => {
		let url = '/content/'.concat(id);
		return (
      <a href={url}>		
			<img src={contentData.poster} alt={contentData.title}/>
      </a>		
		);
	}
   

return (          
      <div className ="containerMovie">
          <div className ="featuresMovie">
            <p className ="title"><h3>{movie.title}</h3></p>
            <img className ="poster" src={movie.poster}/>
            <p className ="description">{movie.description}</p>
            <button className="reproduceButn">Boton</button>
          </div>
        <p className ="relatedContentTitle"><h4>Related Content</h4></p>
        <div className ="relatedContent">
          {movie.relatedContent.map( cont => poster(cont, cont.id))}           
        </div>
    </div>
   );
  

  
}

// const RedirectToContent = (cont) => {
//   const click = () => props.history.push ('/content/${cont.id}')
//   return (
//       <button type = "button"  onClick = {click}>redirect</button>
//   );
// }
//  if ( match.params.id contains "ser"){
  //    push (formatea la serie )
  //  } 
  //  else 
  //  {
  //    push el que te formatea la peli 
  //  }

  //     /content  te devuelve todas las pelis 
  //     /content/id_pelicula o serie 
      
    //  selectContent=(cont)=>{
    // //   //  <BrowserRouter>
    // //   //  <Switch>
    // //   //    <Route path = "/content/:id" component = {RedirectToContent}/>
    // //   //  </Switch>
    // //   //  </BrowserRouter>
    
    // }

export default Movie;