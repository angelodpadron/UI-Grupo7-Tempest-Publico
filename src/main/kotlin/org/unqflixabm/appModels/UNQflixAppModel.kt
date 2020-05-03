package org.unqflixabm.appModels

import data.getUNQFlix
import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class UNQflixAppModel {

    private var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var categories: MutableList<CategoryAppModel> = initCategories()
    var selectSerie: SeriesAppModel? = null // selecciona serie aunque parezca que seleccione el id
    //Fields to add a New Serie
    var id = ""
    var title = ""
    var description = ""
    var poster = ""
    var stateSerie:ContentState = Unavailable()
    var categoriesSerie: MutableList<Category> = mutableListOf()
    var seasonsSerie: MutableList<Season> = mutableListOf()
    var relatedContentSerie: MutableList<Content> = mutableListOf()
    //

    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }
    fun initCategories(): MutableList<CategoryAppModel> {
        return system.categories.map { CategoryAppModel(it) }.toMutableList()
    }
    

    //EXCEPTIONS
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
            throw NonSelectException("Please select a serie before continue")
        }
    }
    fun catchExistSerieException(){
        var unqflix : UNQflixAppModel = this

        try{
           unqflix.addSerie()
        }
        catch( e : ExistsException){
            UserException(e.message)
        }
    }
   
    //ALTA
    fun newSerie(): Serie{
        return Serie(id,title,description,poster,stateSerie,categoriesSerie,seasonsSerie,relatedContentSerie)
    }
    fun addSerie(){
        //agregar al modelo
        system.addSerie(newSerie())
        //update viewmodel
        series = initSeries()
    }

    //BAJA
    fun deleteSerie(selectSerie:SeriesAppModel?){
        //TODO: excepciones!
        if (selectSerie != null) {
            system.deleteSerie(selectSerie.id)
        }
        series = initSeries()
    }

    //MODIFICACION

}