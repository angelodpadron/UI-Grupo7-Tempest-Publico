package org.unqflixabm

import data.getUNQFlix
import domain.*
import org.uqbar.commons.model.annotations.Observable

@Observable

class SeriesAppModel (var serie: Serie) {

    var model = serie

    var id = ""
    var title = ""
    var description = ""
    var poster = ""
    var state : ContentState
    var categories: MutableList<CategoryAppModel> = mutableListOf()
    var seasons: MutableList<SeasonAppModel> = mutableListOf()
    var relatedContent: MutableList<ContentAppModel> = mutableListOf()

    init {
        this.id = serie.id
        this.title = serie.title
        this.description = serie.description
        this.poster = serie.poster
        this.state = serie.state
        this.categories = initCategories()
        this.seasons = initSeasons()
        this.relatedContent = initContents()
    }

    fun initSeasons(): MutableList<SeasonAppModel>{
        return serie.seasons.map{SeasonAppModel(it)}.toMutableList()
    }
    fun initCategories(): MutableList<CategoryAppModel>{
        return serie.categories.map{CategoryAppModel(it)}.toMutableList()
    }
    fun initContents(): MutableList<ContentAppModel>{
        return serie.relatedContent.map{ContentAppModel(it)}.toMutableList()
    }

    //querys

    fun getCantSeasons(): Int = this.seasons.size

}