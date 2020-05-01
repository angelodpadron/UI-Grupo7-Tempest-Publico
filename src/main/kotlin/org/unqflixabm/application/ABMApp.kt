package org.unqflixabm.application

import org.unqflixabm.appModel.UNQflixAppModel
import org.unqflixabm.windows.WindowSeries
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class ABMApp: Application() {

    override fun createMainWindow(): Window<*> {
        return WindowSeries(this, UNQflixAppModel())
    }
}

fun main(){
    ABMApp().start()
}