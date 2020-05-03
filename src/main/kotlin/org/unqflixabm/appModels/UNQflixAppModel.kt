package org.unqflixabm.appModels

import data.getUNQFlix
import domain.UNQFlix
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class UNQflixAppModel {

    private var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var categories: MutableList<CategoryAppModel> = initCategories()
    var selectSerie: SeriesAppModel? = null // selecciona serie aunque parezca que seleccione el id


    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }

    fun getSerie(selectSerie: String?): SeriesAppModel?{
        return series.find {it.id == selectSerie }

    fun initCategories(): MutableList<CategoryAppModel> {
        return system.categories.map { CategoryAppModel(it) }.toMutableList()
    }
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
   
    //ALTA
    fun addSerie(seriesAppModel: SeriesAppModel){
        //TODO: excepciones!
        //agregar al modelo
        system.addSerie(seriesAppModel.model())
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