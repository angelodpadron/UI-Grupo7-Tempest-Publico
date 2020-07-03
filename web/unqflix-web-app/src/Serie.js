import React, {useState, useEffect} from 'react';
import api from './Api'

const seriePrueba = {
    "id": "ser_1",
    "title": "I Am Not an Animal",
    "description": "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
    "poster": "https://image.tmdb.org/t/p/w500/nMhv6jG5dtLdW7rgguYWvpbk0YN.jpg",
    "categories": [
        {
            "name": "Animation"
        },
        {
            "name": "Comedy"
        }
    ],
    "relatedContent": [
        {
            "id": "mov_22",
            "description": "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
            "title": "Fight Club",
            "state": false
        },
        {
            "id": "mov_20",
            "description": "When larcenous real estate clerk Marion Crane goes on the lam with a wad of cash and hopes of starting a new life, she ends up at the notorious Bates Motel, where manager Norman Bates cares for his housebound mother. The place seems quirky, but fine… until Marion decides to take a shower.",
            "title": "Psycho",
            "state": true
        },
        {
            "id": "mov_18",
            "description": "A samurai answers a village's request for protection after he falls on hard times. The town needs protection from bandits, so the samurai gathers six others to help him teach the people how to defend themselves, and the villagers provide the soldiers with food. A giant battle occurs when 40 bandits attack the village.",
            "title": "Seven Samurai",
            "state": true
        },
        {
            "id": "mov_56",
            "description": "On an isolated island in Brittany at the end of the eighteenth century, a female painter is obliged to paint a wedding portrait of a young woman.",
            "title": "Portrait of a Lady on Fire",
            "state": true
        },
        {
            "id": "mov_52",
            "description": "Spanning four decades, from the chaotic 1960s to the present, director Marco Tullio Giordana's passionate epic 'La Meglio Gioventu' follows two Italian brothers through some of the most tumultuous events of recent Italian history.",
            "title": "The Best of Youth",
            "state": false
        }
    ],
    "seasons": [
        {
            "id": "sea_1",
            "title": "Specials",
            "description": "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution",
            "poster": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg",
            "chapters": [
                {
                    "id": "cha_1",
                    "title": "I am an Animation",
                    "description": "DVD Making of Documentary",
                    "duration": 50,
                    "video": "https://www.youtube.com/watch?v=Kxms-OtUXS0",
                    "thumbnail": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg"
                }
            ]
        },
        {
            "id": "sea_2",
            "title": "Season 1",
            "description": "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution",
            "poster": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg",
            "chapters": [
                {
                    "id": "cha_2",
                    "title": "London Calling",
                    "description": "Julian Lovely and his group of Animal Activists breaks into Vivi-sec and sets a batch of talking animals free, led by Philip the horse, the group hijack a van, but end up crashing.",
                    "duration": 60,
                    "video": "https://www.youtube.com/watch?v=Kxms-OtUXS0",
                    "thumbnail": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg"
                },
                {
                    "id": "cha_3",
                    "title": "Planet of the Men and Women",
                    "description": "The six liberated talking animals enter the 'planet of the men and women' where they encounter wild animals & have a close shave at an army firing range.",
                    "duration": 50,
                    "video": "https://www.youtube.com/watch?v=Kxms-OtUXS0",
                    "thumbnail": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg"
                },
                {
                    "id": "cha_4",
                    "title": "Money",
                    "description": "The animals begin to have problems with money.",
                    "duration": 90,
                    "video": "https://www.youtube.com/watch?v=Kxms-OtUXS0",
                    "thumbnail": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg"
                },
                {
                    "id": "cha_5",
                    "title": "My Fair Mare",
                    "description": "Love is in the air, but the consequences aren't pretty.",
                    "duration": 30,
                    "video": "https://www.youtube.com/watch?v=Kxms-OtUXS0",
                    "thumbnail": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg"
                },
                {
                    "id": "cha_6",
                    "title": "A Star is Hatched",
                    "description": "Stardom hails for one of the animals but how will the others react?",
                    "duration": 60,
                    "video": "https://www.youtube.com/watch?v=Kxms-OtUXS0",
                    "thumbnail": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg"
                },
                {
                    "id": "cha_7",
                    "title": "Home",
                    "description": "As the reality show comes to an end, the animals are set free.",
                    "duration": 60,
                    "video": "https://www.youtube.com/watch?v=Kxms-OtUXS0",
                    "thumbnail": "https://ih1.redbubble.net/image.349118470.6262/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg"
                }
            ]
        }
    ]
}

const Serie = (props) => {
    //const [serie, setSerie] = useState(seriePrueba);
    const [serie, setSerie] = useState(undefined);
    //const [serieID] = useState(seriePrueba.id);
    const [id] = "ser_1";
    const [seasonID, setSeason] = useState(undefined);
    const [chapters, setChapters] = useState([]);
    const [loading, setLoading] = useState(true);

    function Temporadas({serie}){
        let {seasons} = serie
        return (bloque)
    }

    useEffect( () => {
        //serieInit
        api.getContentId(id)
        .then(response => {
            setSerie(response.data)
            setLoading(false)
        })
    })

    if (loading){
        return(
          <h1>Loading...</h1>
        )
    }

    const renderTableSeasons = () => {
        return serie.seasons.map((season) => {
            //const { id, title } = season //destructuring
            return (
                <th key={season.id} className=''>
                    <td><button type="button" className='seasonSelection' onClick={this.setSeason(season.id)}>{season.title}</button></td>
                </th>
            )
        })
    }

    const TableSeasons = renderTableSeasons

    const renderTableChapters = () => {
        if (this.seasonID === void(0)) {
        
        } else {
            return serie.seasons.map((season) => {
                if (season.id == this.seasonID) {
                    season.chapters.map((chapter) => {
                        return (
                            <tr key={season.id}>
                                <td>{chapter.title}</td>
                            </tr>
                        )
                    })
                }
            })
        }
    }

    const TableChapters = renderTableChapters

    return(
        <div>
            <div className="content">
                <p className="title">{serie.title}</p>
                <p className="description">{serie.description}</p>
            </div>
            <div className="banner">
                <img src={serie.poster} alt="edit" className="poster"></img>
            </div>
            <div className="seasons">
                <table>
                    <TableSeasons ></TableSeasons>
                    <TableChapters></TableChapters>
                </table>
            </div>
            <div className="relatedContent">
    
            </div> 
        </div> // TODO: armar contenido relacionado
    );
}



/*class SerieDetail extends React.Component{
    constructor (props){
        super(props);
        this.state = {
            selectedContent:undefined
        }
        this.active_season_id = ""
    }



    renderTableSeasons() {
        return serie.seasons.map((season) => {
            const { id, title } = season //destructuring
            return (
                <th key={id} className=''>
                    <td><button type="button" className='seasonSelection' onClick={this.updateSelectedSeason(id)}>{title}</button></td>
                </th>
            )
        })
    }

    updateSelectedSeason(id_of_season) {
        this.active_season_id = id_of_season
        this.render() //TODO: encontrar cómo disparar un re render
    }

    renderTableChapters() {
        if (this.active_season_id == "") {
            return ""
        } else {
            return serie.seasons.map((season) => {
                if (season.id == this.active_season_id) {
                    season.chapters.map((chapter) => {
                        return (
                            <tr key={season.id}>
                                <td>{chapter.title}</td>
                            </tr>
                        )
                    })
                }
            })
        }
    }

    render() {
        return(
            <>
                <div className="content">
                    <p className="title">{serie.title}</p>
                    <p className="description">{serie.description}</p>
                </div>
                <div className="banner">
                    <img src={serie.poster} alt="edit" className="poster"></img>
                </div>
                <div className="seasons">
                    <table>
                        {this.renderTableSeasons()}
                        {this.renderTableChapters()}
                    </table>
                </div>
                <div className="relatedContent">

                </div>
            </> // TODO: armar contenido relacionado
        );
    }
}*/

export default Serie;