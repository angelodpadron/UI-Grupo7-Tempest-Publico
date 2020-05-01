package org.unqflixabm

import data.getUNQFlix
import domain.Serie
import domain.UNQFlix
import org.uqbar.commons.model.annotations.Observable

@Observable

class UNQflixAppModel {

    var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var selectSerie: SeriesAppModel? = null                 //seleccionado en tabla

    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }

    fun deleteSerie(selectSerie : SeriesAppModel?){
        series.removeIf { it.id == selectSerie?.id }
    }

    //ALTA

    //BAJA

    //MODIFICACION
}