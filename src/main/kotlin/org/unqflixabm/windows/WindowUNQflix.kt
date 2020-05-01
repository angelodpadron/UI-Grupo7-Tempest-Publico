package org.unqflixabm.windows

import domain.UNQFlix
import org.unqflixabm.appModels.SeriesAppModel
import org.unqflixabm.appModels.UNQflixAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*

class WindowUNQflix (owner: WindowOwner, model: UNQflixAppModel):
    SimpleWindow<UNQflixAppModel>(owner, model ){

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
                fixedSize = 75
                bindContentsTo("id")
            }
            column {
                title = "Title"
                fixedSize = 300
                bindContentsTo("title")
            }
            column {
                title = "#Season"
                fixedSize = 75
                bindContentsTo("numberOfSeasons")
            }
            column {
                title = "State"
                fixedSize = 225
                bindContentsTo("state")
                //TODO: extraer estado real
            }
        }

        Panel (p0) with {
            asHorizontal()
            Button(it) with {
                caption = "Add new Serie"
                //TODO: onClick
            }
            Button(it) with {
                caption = "Modify Serie"
                //TODO: onClick
            }
            Button(it) with {
                caption = "Delete Serie"
                onClick({confirmDelete().open()})
            }
            Button(it) with {
                caption = "Show Serie"
                //TODO: onClick
            }
        }
    }
    private fun confirmDelete()= ConfirmDeleteSerieDialog(owner,modelObject)
    //TODO: armar funciones para botones

}