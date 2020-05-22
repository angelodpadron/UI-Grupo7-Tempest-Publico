package org.unqflixabm.windows

import org.unqflixabm.appModels.SeriesAppModel
import org.unqflixabm.appModels.UNQflixAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.KeyWordTextArea
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class WindowUNQflix (owner: WindowOwner, model: UNQflixAppModel):
    SimpleWindow<UNQflixAppModel>(owner, model ){

    var modelo: UNQflixAppModel =modelObject

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "UnqFlix"

        Panel (p0) with {
            asHorizontal()

            Label(it) with { text = "Search" }
            KeyWordTextArea(it) with {
                height = 20
                width = 400

                bindTo("searchString")
            }
            Button(it) with {
                caption = "Go"
                onClick { tryCatchNotFoundSerieException()
                          modelo.searchSerie() }
            }
        }

        table<SeriesAppModel>(p0) {

            bindSelectionTo("selectSerie")
            title = "Series"
            bindItemsTo("series")
            visibleRows = 10
            column {
                title = "Title"
                fixedSize = 200
                bindContentsTo("title")
            }
            column {
                title = "Seasons"
                fixedSize = 200
                bindContentsTo("numberOfSeasons")
            }
            column {
                title = "ID"
                fixedSize = 200
                bindContentsTo("id")
            }
            column {
                title = "Available?"
                fixedSize = 200
                align("center")
                background = Color.decode("#3A383B")
                color = Color.WHITE
                bindContentsTo("stateToView")
            }
        }

        Panel (p0) with {
            asHorizontal()

            Button(it) with {
                caption = "Add new Serie"
                bgColor = Color.decode("#580AAF")
                width = 200
                onClick({WindowAddSerie(owner,modelo).open()})
            }
            Button(it) with {
                caption = "Modify Serie"
                bgColor = Color.decode("#580AAF")
                width = 200
                onClick {tryNonSelectException()
                    WindowModifieSerie(owner,modelo.selectSerie).open()}
            }
            Button(it) with {
                caption = "Delete Serie"
                bgColor = Color.decode("#580AAF")
                width = 200
                onClick {tryNonSelectException()
                    confirmDelete().open()}
            }
            Button(it) with {
                caption = "Show Serie"
                bgColor = Color.decode("#580AAF")
                width = 200
                onClick {tryNonSelectException()
                    WindowShowSerie(owner,modelo.selectSerie).open()}

            }
        }
    }
    private fun tryNonSelectException()=modelObject.catchNonSelectSerieException(modelObject.selectSerie)
    private fun confirmDelete()= ConfirmDeleteSerieDialog(owner,modelObject)
    private fun tryCatchNotFoundSerieException() = modelObject.catchNotFoundSerieException()
}