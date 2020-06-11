package org.unqflixabm.appModels

import data.idGenerator
import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class SeriesAppModel (var serie : Serie){
    var id = serie.id
    var title = serie.title
    var description = serie.description
    var poster = serie .poster
    var state = serie.state
    var categories: MutableList<CategoryAppModel> = initCategories()
    var seasons: MutableList<SeasonAppModel> = initSeasons()
    var numberOfSeasons = seasons.count()
    var relatedContent :MutableList<ContentAppModel> = initContents()

    //SELECTIONS

    var selectSeason: SeasonAppModel? = null
    var selectCategorySerie : CategoryAppModel? = null
    var selectContentSerie: ContentAppModel? = null

    init {
        id
        title
        description
        poster
        state
        categories
        seasons
        numberOfSeasons
        relatedContent
    }

    constructor(): this(Serie("","","","",Unavailable(),categories = mutableListOf(),seasons = mutableListOf(),relatedContent = mutableListOf()))

    //INITIATORS mapper appModel

    private fun initSeasons(): MutableList<SeasonAppModel>{
        return serie.seasons.map { SeasonAppModel(it) }.toMutableList()
    }

    private fun initCategories(): MutableList<CategoryAppModel> {
        return serie.categories.map { CategoryAppModel(it) }.toMutableList()
    }

    private fun initContents(): MutableList<ContentAppModel>{
        return serie.relatedContent.map { ContentAppModel(it) }.toMutableList()
    }

    //ADDITIONS

    fun addSeason (season: Season){

        try{
            serie.addSeason(season)
            seasons = initSeasons()
            numberOfSeasons = seasons.count()
        }
        catch(e: ExistsException) {
            throw UserException(e.message)
        }
    }

    fun addCategory(categoryAppModel: CategoryAppModel){
        try {
            if(serie.categories.any { it.id == categoryAppModel.id})throw ExistsException(categoryAppModel.toModel())
            serie.categories.add(categoryAppModel.toModel())
            categories = initCategories()
        }
        catch (e : ExistsException){
            throw UserException(e.message)
        }
    }

    fun addContent(contentAppModel: ContentAppModel){
        try {
            if(serie.relatedContent.any { it.id == contentAppModel.id})throw ExistsException(contentAppModel.toModel())
            serie.relatedContent.add(contentAppModel.toModel())
            relatedContent = initContents()
        }
        catch (e : ExistsException){
            throw UserException(e.message)
        }
    }

    //DELETIONS

    fun removeCategory(categoryAppModel: CategoryAppModel){
        serie.categories.remove(categoryAppModel.toModel())
        categories = initCategories()
    }

    fun removeContent(contentAppModel: ContentAppModel){
        serie.relatedContent.remove(contentAppModel.toModel())
        relatedContent = initContents()
    }

    //QUERYS

    fun newSerieFormat(): Serie{

        return Serie(idGenerator.nextSerieId(),title,description, poster, state, mutableListOf() /*categories.map {it.toModel()} as MutableList<Category>*/, mutableListOf(), mutableListOf())
    }

    //EXCEPTIONS

    fun catchNonSelectSeasonException(selectSeason: SeasonAppModel?) {
        try {
            this.nonSelectSeasonException(selectSeason)
        } catch (e: NonSelectException) {
            throw UserException(e.message)
        }
    }

    fun nonSelectSeasonException(selectSeason: SeasonAppModel?) {
        if (selectSeason == null) {
            throw NonSelectException("Please select a season before proceeding")
        }
    }
}



