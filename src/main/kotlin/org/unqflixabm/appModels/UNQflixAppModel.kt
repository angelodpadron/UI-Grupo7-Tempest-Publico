package org.unqflixabm.appModels

import data.getUNQFlix
import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import support.itemFromList as dasdasdasdasdasdasd

@Observable

class UNQflixAppModel {

    private var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var categories: MutableList<CategoryAppModel> = initCategories()
    var contents: MutableList<ContentAppModel> = initContents()
    var selectSerie: SeriesAppModel? = null // selecciona serie aunque parezca que seleccione el id
    var searchString: String = ""
    val lastSeriesId: String = this.series.last().id
    //Required fields to add a New Serie
    var idGenerator : IdGenerator = IdGenerator()
    var title = ""
    var description = ""
    var poster = ""
    var stateSerie:ContentState = Unavailable()
    var categoriesSerie: MutableList<Category> = mutableListOf()
    var seasonsSerie: MutableList<Season> = mutableListOf()
    var relatedContentSerie: MutableList<Content> = mutableListOf()
    var selectContent: ContentAppModel? = null
    var selectCategory: CategoryAppModel? = null
    //

    //INITIATORS

    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }
    fun initCategories(): MutableList<CategoryAppModel> {
        return system.categories.map { CategoryAppModel(it) }.toMutableList()
    }
    fun initContents(): MutableList<ContentAppModel> {
        return system.banners.map { ContentAppModel(it) }.toMutableList()
    }

    //EXCEPTIONS

    /*
    @Function  control that a series is selected before interact with him
     */
    fun catchNonSelectSerieException(selectSerie: SeriesAppModel?){
        try{
            this.nonSelectSerieException(selectSerie)
        }
        catch(e: NonSelectException){
            throw UserException(e.message)
        }
    }
    fun nonSelectSerieException(selectSerie: SeriesAppModel?){
        if (selectSerie == null) {
            throw NonSelectException("Please select a show before continue")
        }
    }

    /*
    @Function  control that the newSerie To add wasn't added before
     */
    fun catchExistSerieException() {
        var unqflix: UNQflixAppModel = this
        try {
            unqflix.addSerie()
        }
        catch (e: ExistsException) {
            throw UserException(e.message)
        }
    }

    /*
    @Function  verify that searched series are added in the system if not throw mssg exception
     */
    fun catchNotFoundSerieException(){
        var unqflix: UNQflixAppModel = this
        try {
            unqflix.searchSerie()
        }
        catch (e: NotFoundException) {
            throw UserException(e.message)
        }
    }

    //QUERYS

    fun searchSerie(){
        if(series.any { it.title.contains(searchString,true)}){
            series = system.searchSeries(searchString).map { SeriesAppModel(it) }.toMutableList()
        }
        else
        {
            throw NotFoundException("Serie","Title",searchString)
        }
    }
    fun getNextSerieId():String {

        var lastSerieId: String
        if (this.series.isEmpty()) {
            lastSerieId = "ser_1"
        }
        else {
            lastSerieId = this.series.last().id
            lastSerieId = "ser_${(lastSerieId.split("_").last()).toInt() + 1}"
        }
        return lastSerieId
    }

    //ADDS

    fun newSerie(): Serie{
        return Serie(getNextSerieId(),title,description,poster,stateSerie,categoriesSerie,seasonsSerie,relatedContentSerie)
    }
    fun addSerie(){
        try {
            //agregar al modelo
            system.addSerie(newSerie())
            //update viewmodel
            series = initSeries()
        }
        catch (e: ExistsException) {
            throw UserException(e.message)
        }
    }
    fun addSerieCategory(selectCategory: CategoryAppModel?) {
        SeriesAppModel(newSerie()).addCategory(selectCategory)
    }
    fun addSerieContent(selectContent: ContentAppModel?){
        SeriesAppModel(newSerie()).addContent(selectContent)
    }

    //DELETES

    fun deleteSerie(selectSerie:SeriesAppModel?){
        if (selectSerie != null) {
            system.deleteSerie(selectSerie.id)
        }
        series = initSeries()
        }
    }