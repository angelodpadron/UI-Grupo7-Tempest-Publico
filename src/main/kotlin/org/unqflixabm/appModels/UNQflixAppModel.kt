package org.unqflixabm.appModels

import data.getUNQFlix
import domain.UNQFlix
import org.uqbar.commons.model.annotations.Observable

@Observable

class UNQflixAppModel {

    var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var selectSerie:String? = null
    //Todo selectSerie selecciona un id de serie en la tabla ,cambiar nombre a selectIdSerie?
    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }

    fun deleteSerie(selectSerie : String?){
        series.removeIf { it.id == selectSerie }
    }

    fun getSerie(selectSerie: String?): SeriesAppModel? {

        return series.find {it.id == selectSerie }
    }

    //ALTA

    //BAJA

    //MODIFICACION
}