package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.unqflixabm.appModels.SeriesAppModel
import org.uqbar.arena.widgets.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.windows.SimpleWindow

class WindowShowSerie (owner: WindowOwner, seriesAppModel: SeriesAppModel):
    SimpleWindow<SeriesAppModel>(owner, seriesAppModel){
        override fun addActions(p0: Panel?) {
        }

        override fun createFormPanel(p0: Panel) {
            title = ""

            Label(p0) with {
                //TODO: text = getSerieTitle()
            }

            table<SeasonAppModel>(p0) {
                title = "Seasons:"
                bindItemsTo("seasons")
                //TODO: bindSelectionTo
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
                    title = "#Chapter"
                    fixedSize = 100
                    bindContentsTo("numberOfChapters")
                }
            }

            Panel(p0) with {
                asHorizontal()
                Button(it) with {
                    caption = "Add new season"
                    //TODO: onClick
                }
                Button(it) with {
                    caption = "Modify season"
                    //TODO: onClick
                }
                Button(it) with {
                    caption = "Show chapters"
                    //TODO: onClick
                }
            }
        }
    }