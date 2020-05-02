package org.unqflixabm.appModels

import data.getUNQFlix
import domain.UNQFlix
import org.uqbar.commons.model.annotations.Observable

@Observable

class UNQflixAppModel {

    var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var selectSerie:SeriesAppModel? = null // selecciona serie aunque parezca que seleccione el id

    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }

    fun deleteSerie(selectSerie:SeriesAppModel?){
        series.removeIf { it.id == selectSerie?.id }
    }

    fun getSerie(selectSerie: String?): SeriesAppModel?{
        return series.find {it.id == selectSerie }
    }
  
    //ALTA

    //BAJA

    //MODIFICACION
}