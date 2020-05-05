package org.unqflixabm.appModels

import data.getUNQFlix
import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class SeriesAppModel (private var model: Serie) {
    var systemCategories: MutableList<Category> = getUNQFlix().categories
    var id = ""
    var title = ""
    var description = ""
    var poster = ""
    var state: ContentState
    var categoriesSyst: MutableList<CategoryAppModel> = mutableListOf()
    var categories: MutableList<CategoryAppModel> = mutableListOf()
    var seasons: MutableList<SeasonAppModel> = mutableListOf()
    var numberOfSeasons: Int
    var selectSeason: SeasonAppModel? = null
    var relatedContent: MutableList<ContentAppModel> = mutableListOf()

    //Required fields to create a new Season
    var titleSeason: String = ""
    var descriptionSeason: String = ""
    var posterSeason: String = ""
    var chaptersSeason: MutableList<Chapter> = mutableListOf()
    //

    init {
        this.id = model.id
        this.title = model.title
        this.description = model.description
        this.poster = model.poster
        this.state = model.state
        this.categories = initCategories()
        this.seasons = initSeasons()
        this.numberOfSeasons = this.seasons.count()
        this.relatedContent = initContents()
        this.categoriesSyst = initCategoriesSyst()
    }

    //INITIATORS

    fun initSeasons(): MutableList<SeasonAppModel> {
        return model.seasons.map { SeasonAppModel(it) }.toMutableList()
    }

    fun initCategories(): MutableList<CategoryAppModel> {
        return model.categories.map { CategoryAppModel(it) }.toMutableList()
    }

    fun initContents(): MutableList<ContentAppModel> {
        return model.relatedContent.map { ContentAppModel(it) }.toMutableList()
    }

    fun initCategoriesSyst(): MutableList<CategoryAppModel> {
        return systemCategories.map { CategoryAppModel(it) }.toMutableList()
    }

    //TO MODEL

    fun toModel() = model

    //ADDS

    fun newSeason(): Season {
        return Season(getNextSeasonId(), titleSeason, descriptionSeason, posterSeason, chaptersSeason)
    }

    fun addSeason() {
        try {
            //addSeasonToModel
            model.addSeason(newSeason())
            //update viewmodel
            seasons = initSeasons()
            numberOfSeasons = this.seasons.count()
        } catch (e: ExistsException) {
            throw UserException(e.message)
        }
    }

    fun addContent(selectContent: ContentAppModel?) {
        if (selectContent != null) {
            //addContentToModel
            model.relatedContent.add(selectContent.toModel())
            //update viewModel
            relatedContent = initContents()
        }
    }

    fun addCategory(selectCategory: CategoryAppModel?) {
        if (selectCategory != null) {
            //addCategoryToModel
            model.categories.add(selectCategory.toModel())
            //update viewmodel
            categories = initCategories()
        }
    }

    //QUERYS

    fun getNextSeasonId(): String {
        val lastSeasonId: String = this.seasons.last().id
        //TODO : recordar hacer funcion
        var nextId: String = "sea_${(lastSeasonId.split("_").last()).toInt() + 1}"

        return nextId
    }

    //EXCEPTIONS

    /*
   @Function  control that a season is selected before interact with him
   */
    fun catchNonSelectSeasonException(selectSeason: SeasonAppModel?) {
        try {
            this.nonSelectSeasonException(selectSeason)
        } catch (e: NonSelectException) {
            throw UserException(e.message)
        }
    }

    fun nonSelectSeasonException(selectSeason: SeasonAppModel?) {
        if (selectSeason == null) {
            throw NonSelectException("Please select a season before continue")
        }
    }
}
    //transform (temporal)
    //private fun fromState(cs: ContentState): Boolean = cs.javaClass == Available().javaClass

    //private fun fromBoolean(b: Boolean): ContentState{
    //    return if (b){
    //        Available()
    //    }
    //    else{
    //        Unavailable()
    //    }
    //}



