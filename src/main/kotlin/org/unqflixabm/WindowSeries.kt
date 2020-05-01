package org.unqflixabm

import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*

class WindowSeries (owner: WindowOwner, unqflixAppModel: UNQflixAppModel):
    SimpleWindow<UNQflixAppModel>(owner, unqflixAppModel){

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "UnqFlix"

        Panel (p0) with {
            asHorizontal()
            Label(it) with { text = "Search" }
            TextBox(it) with {
                width = 400
                //TODO: bindTo("")
            }
            //TODO: ver cómo hacer búsqueda
        }

        Label (p0) with {
            text = "Series:"
        }
        table<SeriesAppModel>(p0) {
            bindItemsTo("series")
            //TODO: bindSelectionTo("")
            column {
                title = "#"
                fixedSize = 100
                bindContentsTo("id")
            }
            column {
                title = "Title"
                fixedSize = 300
                bindContentsTo("title")
            }
            column {
                title = "#Season"
                fixedSize = 100
                bindContentsTo("seasons")
            }
            column {
                title = ""

                title = "State"

                fixedSize = 100
                bindContentsTo("state")
            }
        }

        Panel (p0) with {
            asHorizontal()
            Button(it) with {
                caption = "Add new series"
                //TODO: onClick
            }
            Button(it) with {
                caption = "Modify series"
                //TODO: onClick
            }
            Button(it) with {
                caption = "Delete series"
                //TODO: onClick
            }
            Button(it) with {
                caption = "Show series"
                //TODO: onClick
            }
        }

        //TODO: armar funciones para botones
    }
}