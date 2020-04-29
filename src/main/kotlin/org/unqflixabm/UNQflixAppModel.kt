package org.unqflixabm

import data.getUNQFlix
import domain.Serie
import domain.UNQFlix

class UNQflixAppModel {

    var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var selectSerie: SeriesAppModel? = null                 //seleccionado en tabla

    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }

    //ALTA

    //BAJA

    //MODIFICACION
}