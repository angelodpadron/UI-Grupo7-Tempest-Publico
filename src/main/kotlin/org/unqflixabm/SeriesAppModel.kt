package org.unqflixabm

import data.getUNQFlix
import domain.*

class SeriesAppModel (var serie: Serie) {

    var model = serie

    var id = ""
    var title = ""
    var description = ""
    var poster = ""
    var state : ContentState
    var categories: MutableList<Category> = mutableListOf()
    var seasons: MutableList<Season> = mutableListOf()
    var relatedContent: MutableList<Content> = mutableListOf()

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

}