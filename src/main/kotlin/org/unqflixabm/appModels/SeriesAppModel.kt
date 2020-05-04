package org.unqflixabm.appModels

import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class SeriesAppModel (private var model: Serie) {

    var id = ""
    var title = ""
    var description = ""
    var poster = ""
    var state : ContentState
    var categories: MutableList<CategoryAppModel> = mutableListOf()
    var seasons: MutableList<SeasonAppModel> = mutableListOf()
    var numberOfSeasons: Int
    var selectSeason: SeasonAppModel? =null
    var relatedContent: MutableList<ContentAppModel> = mutableListOf()
    //Fields to create a new Season
    var idSeason : String = ""
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
    }

    fun initSeasons(): MutableList<SeasonAppModel>{
        return model.seasons.map{ SeasonAppModel(it) }.toMutableList()
    }
    fun initCategories(): MutableList<CategoryAppModel>{
        return model.categories.map{ CategoryAppModel(it) }.toMutableList()
    }
    fun initContents(): MutableList<ContentAppModel>{
        return model.relatedContent.map{ ContentAppModel(it) }.toMutableList()
    }

    //TO MODEL

    fun toModel() = model

    //ADDS
    fun newSeason():Season {
        return Season(idSeason,titleSeason,descriptionSeason,posterSeason,chaptersSeason)
    }
    fun addSeason(){
        //addSeasonToModel
        model.addSeason(newSeason())
        //update viewmodel
        this.initSeasons()
    }
    fun addContent(selectContent: ContentAppModel?) {
        if (selectContent != null) {
            //addContentToModel
            model.relatedContent.add(selectContent.toModel())
        }
        //update viewModel
        this.initContents()
    }
    fun addCategory(selectCategory: CategoryAppModel?) {
        if (selectCategory != null) {
            //addCategoryToModel
            model.categories.add(selectCategory.toModel())
        }
        //update viewmodel
        this.initCategories()
    }

    //QUERYS
    fun getCantSeasons(): Int = this.seasons.size
  
    //EXCEPTIONS
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
    fun catchExistSeasonException(){

        var serie: SeriesAppModel = this

        try{
            serie.addSeason()
        }
        catch( e : ExistsException){
            UserException(e.message)
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



}