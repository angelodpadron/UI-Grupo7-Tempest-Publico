package org.unqflixabm

import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowSeries (owner: WindowOwner, unqflixAppModel: UNQflixAppModel):
    SimpleWindow<UNQflixAppModel>(owner, unqflixAppModel){

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel?) {
        title = "ABM Control Panel"

    }
}