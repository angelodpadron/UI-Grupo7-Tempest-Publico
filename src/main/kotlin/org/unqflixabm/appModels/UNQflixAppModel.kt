package org.unqflixabm.appModels

import data.getUNQFlix
import domain.UNQFlix
import org.uqbar.commons.model.annotations.Observable

@Observable

class UNQflixAppModel {

    private var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var selectSerie:SeriesAppModel? = null // selecciona serie aunque parezca que seleccione el id

    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }

    fun getSerie(selectSerie: String?): SeriesAppModel?{
        return series.find {it.id == selectSerie }
    }
  
    //ALTA
    fun addSerie(seriesAppModel: SeriesAppModel){
        //TODO: excepciones!
        //agregar al modelo
        system.addSerie(seriesAppModel.model())
        //update viewmodel
        this.initSeries()
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