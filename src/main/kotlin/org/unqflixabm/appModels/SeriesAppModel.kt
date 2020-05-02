package org.unqflixabm.appModels

import domain.*
import org.uqbar.commons.model.annotations.Observable

@Observable
class SeriesAppModel (var serie: Serie) {

    var model = serie

    //from Id
    var id = ""
    //from Content
    var title = ""
    var description = ""
    var poster = ""
    var state : Boolean
    var relatedContent: MutableList<ContentAppModel> = mutableListOf()
    //from self
    var categories: MutableList<CategoryAppModel> = mutableListOf()
    var seasons: MutableList<SeasonAppModel> = mutableListOf()

    //for view
    var numberOfSeasons: Int


    init {
        this.id = serie.id
        this.title = serie.title
        this.description = serie.description
        this.poster = serie.poster
        this.state = fromState(serie.state)
        this.categories = initCategories()
        this.seasons = initSeasons()
        this.numberOfSeasons = this.seasons.count()
        this.relatedContent = initContents()
    }

    fun initSeasons(): MutableList<SeasonAppModel>{
        return serie.seasons.map{ SeasonAppModel(it) }.toMutableList()
    }
    fun initCategories(): MutableList<CategoryAppModel>{
        return serie.categories.map{ CategoryAppModel(it) }.toMutableList()
    }
    fun initContents(): MutableList<ContentAppModel>{
        return serie.relatedContent.map{ ContentAppModel(it) }.toMutableList()
    }

    //querys

    fun getCantSeasons(): Int = this.seasons.size

    //transform (temporal)

    private fun fromState(cs: ContentState): Boolean = cs.javaClass == Available().javaClass
    private fun fromBoolean(b: Boolean): ContentState{
        return if (b){
            Available()
        }
        else{
            Unavailable()
        }
    }

}