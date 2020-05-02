package org.unqflixabm.appModels

import data.getUNQFlix
import domain.UNQFlix
import org.unqflixabm.exceptions.NonSelectSerieException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class UNQflixAppModel {

    private var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var selectSerie: SeriesAppModel? = null // selecciona serie aunque parezca que seleccione el id

    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }
    fun catchDeleteException(selectSerie: SeriesAppModel?){
        try{
            this.deleteException(selectSerie)
        }
        catch(e: NonSelectSerieException){
            throw UserException(e.message)
        }
    }
    fun deleteException(selectSerie: SeriesAppModel?){
        if (selectSerie == null) {
            throw NonSelectSerieException("Please select a serie before continue")
        }
    }
    fun deleteSerie(selectSerie: SeriesAppModel?) {
        if (selectSerie != null) {
            system.deleteSerie(selectSerie.id)
        }
        series = initSeries()
    }

    fun getSerie(selectSerie: String?): SeriesAppModel? {
        return series.find { it.id == selectSerie }
    }

    //ALTA

    //BAJA

    //MODIFICACION
}