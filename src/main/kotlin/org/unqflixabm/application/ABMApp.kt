package org.unqflixabm.application

import org.unqflixabm.appModels.UNQflixAppModel
import org.unqflixabm.windows.WindowUNQflix
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class ABMApp: Application() {

    override fun createMainWindow(): Window<*> {
        return WindowUNQflix(this, UNQflixAppModel())
    }
}

fun main(){
    ABMApp().start()
}