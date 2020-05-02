package org.unqflixabm.appModels

import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

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
    var numberOfSeasons: Int
    var relatedContent: MutableList<ContentAppModel> = mutableListOf()
    var selectSeason: SeasonAppModel? =null

    init {
        this.id = serie.id
        this.title = serie.title
        this.description = serie.description
        this.poster = serie.poster
        this.state = serie.state
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
    fun catchNonSelectSeasonException(selectSeason: SeasonAppModel?){
        try {
            this.nonSelectSeasonException(selectSeason)
        }
        catch (e : NonSelectException){
            throw UserException(e.message)
        }
    }

    fun nonSelectSeasonException(selectSeason: SeasonAppModel?) {
        if (selectSeason == null) {
            throw NonSelectException("Please select a season before continue")
        }
    }
}