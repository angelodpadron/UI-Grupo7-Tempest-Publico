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

    //----------Required fields to create a new Season
    var titleSeason: String = ""
    var descriptionSeason: String = ""
    var posterSeason: String = ""
    var chaptersSeason: MutableList<Chapter> = mutableListOf()

    //---------Selector For modify serie's categories or relatedContens
    var selectCategorySerie : CategoryAppModel? = null
    var selectContentSerie : ContentAppModel? = null

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

    //---------SetUp

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

    //---------Modify

    fun updateModel(){
        model.title = title
        model.poster = poster
        model.description = description
        model.categories = categories.map { it.toModel() }.toMutableList()
        model.relatedContent = relatedContent.map { it.toModel() }.toMutableList()
        model.state = state


    }

    fun resetModify(){
        this.title = model.title
        this.description = model.description
        this.poster = model.poster
        this.state = model.state
        this.categories = initCategories()
        this.relatedContent = initContents()
    }

    //---------- ViewModel to Model

    fun toModel() = model

    //----------Adds

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

    fun addContent(selectContentVm: ContentAppModel?) {
        if (selectContentVm != null) {
            //addContentToModel
            model.relatedContent.add(selectContentVm.toModel())
            //update viewModel
            this.relatedContent.add(ContentAppModel(selectContentVm.toModel()))
            relatedContent = initContents()
        }
    }

    fun addCategory(selectCategoryVm: CategoryAppModel?) {
        if (selectCategoryVm != null) {
            categories.add(selectCategoryVm)
        }
    }


    fun modifydeleteCategories(selectCategorySerie: CategoryAppModel?){
        if (selectCategorySerie!= null){
            //model.categories.remove(selectCategorySerie.toModel())
            categories.remove(selectCategorySerie)

            //categories = initCategories()
        }

    }

    //----------Deletes

    fun deleteCategory(selectCategoryDom: Category?) {
        if (selectCategoryDom != null) {
            //RemoveCategoryFromModel
            model.categories.remove(selectCategoryDom)
            //update viewmodel
            categories = initCategories()
        }
    }
    fun deleteContent(selectContentDom: Content?) {
        if (selectContentDom != null) {
            //RemoveContentFromModel
            model.relatedContent.remove(selectContentDom)
            //update viewmodel
            categories = initCategories()
        }
    }

    //----------Querys

        fun getNextSeasonId(): String {
            var lastSeasonId: String
            if (this.seasons.isEmpty()) {
                lastSeasonId = "sea_1"
            } else {
                lastSeasonId = this.seasons.last().id
                lastSeasonId = "ser_${(lastSeasonId.split("_").last()).toInt() + 1}"
            }
            return lastSeasonId
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
            throw NonSelectException("Please select a season before continue")
        }
    }
}



