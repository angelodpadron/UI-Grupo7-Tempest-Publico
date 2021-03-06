package org.unqflixabm.appModels

import data.getUNQFlix
import data.idGenerator
import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class UNQflixAppModel {

    val idSystem: IdGenerator = idGenerator
    private var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var categories: MutableList<CategoryAppModel> = initCategories()
    var contents: MutableList<ContentAppModel> = initContents()
    var searchString: String = ""

    //SELECCTIONS

    var selectSerie: SeriesAppModel? = null // selecciona serie aunque parezca que seleccione el id

    //INITIATORS

    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }
    fun initCategories(): MutableList<CategoryAppModel> {
        return system.categories.map { CategoryAppModel(it) }.toMutableList()
    }
    fun initContents(): MutableList<ContentAppModel> {
        val allContent = mutableListOf<ContentAppModel>()
        allContent.addAll(initContentMovies())
        allContent.addAll(initContentSeries())
        return allContent
    }
    fun initContentMovies(): MutableList<ContentAppModel> {
        return system.movies.map { ContentAppModel(it) }.toMutableList()
    }
    fun initContentSeries(): MutableList<ContentAppModel>{
        return system.series.map {ContentAppModel(it)} .toMutableList()
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

        if(system.series.any { it.title.contains(searchString,true)}){
            series = system.searchSeries(searchString).map { SeriesAppModel(it) }.toMutableList()
        }
        else{
            throw NotFoundException("Serie","Title",searchString)
        }
    }

    //ADDITIONS

    fun addSerie(serie : Serie){
        try {
            //agregar al modelo
            system.addSerie(serie)
            //update viewmodel
            series = initSeries()
        }
        catch (e: ExistsException) {
            throw UserException(e.message)
        }
    }

    //DELETIONS

    fun deleteSerie(selectSerie:SeriesAppModel?){
        if (selectSerie != null) {
            system.deleteSerie(selectSerie.id)
        }
        series = initSeries()
    }

}