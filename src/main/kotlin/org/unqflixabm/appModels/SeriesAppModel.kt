package org.unqflixabm.appModels

import domain.*
import org.uqbar.commons.model.annotations.Observable

@Observable
class SeriesAppModel (private var model: Serie) {

    var id = ""
    var title = ""
    var description = ""
    var poster = ""
    var state : Boolean
    var categories: MutableList<CategoryAppModel> = mutableListOf()
    var seasons: MutableList<SeasonAppModel> = mutableListOf()
    var numberOfSeasons: Int
    var relatedContent: MutableList<ContentAppModel> = mutableListOf()

    init {
        this.id = model.id
        this.title = model.title
        this.description = model.description
        this.poster = model.poster
        this.state = fromState(model.state)
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
    fun model(): Serie{
        return model
    }

    //QUERYS

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