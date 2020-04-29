package org.unqflixabm

import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowSeries (owner: WindowOwner, seriesAppModel: SeriesAppModel): SimpleWindow<SeriesAppModel>(owner, seriesAppModel){

    override fun addActions(p0: Panel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createFormPanel(p0: Panel?) {
        title = "ABM Control Panel"

    }
}