package org.unqflixabm.windows

import org.unqflixabm.appModels.CategoryAppModel
import org.unqflixabm.appModels.ContentAppModel
import org.unqflixabm.appModels.SeriesAppModel
import org.unqflixabm.appModels.UNQflixAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List

class WindowModifieSerie(owner: WindowOwner, model: SeriesAppModel?):
    SimpleWindow<SeriesAppModel>(owner,model ) {
    var serie: SeriesAppModel = modelObject

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        Panel(p0) with {
            asHorizontal()
            TextBox(it) with {
                title = "Title"
                bindTo("title")
            }
            TextBox(it) with {
                title = "Poster"
                bindTo("poster")
            }
        }
        Panel(p0) with {
            asHorizontal()
            KeyWordTextArea(it) with {
                title = "Description:"
                bindTo("description")
            }
            CheckBox(it) with {
                title = "State"
                bindTo("state") //TODO: hay que lograr que defina states
            }

        }
        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                //TODO: onClick
            }
            Button(it) with {
                caption = "Cancel"
                //TODO: onClick
            }
        }

    }
}