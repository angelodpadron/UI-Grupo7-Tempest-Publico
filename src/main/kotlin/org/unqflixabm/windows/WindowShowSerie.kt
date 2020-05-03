package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.unqflixabm.appModels.SeriesAppModel
import org.uqbar.arena.widgets.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.windows.SimpleWindow

class WindowShowSerie(owner: WindowOwner, seriesAppModel: SeriesAppModel?):
    SimpleWindow<SeriesAppModel>(owner, seriesAppModel){

    var selectedSerie: SeriesAppModel = modelObject

        override fun addActions(p0: Panel?) {
        }

        override fun createFormPanel(p0: Panel) {
            title = ""

            Label(p0) with {
              text = getSerieTitle().toString()
            }

            table<SeasonAppModel>(p0) {
                title = "Seasons:"
                bindItemsTo("seasons")
                bindSelectionTo("selectSeason")
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
                    onClick({tryNonSelectException()
                             WindowModifieSeason(owner,selectedSerie.selectSeason).open()})
                }
                Button(it) with {
                    caption = "Show chapters"
                    onClick({tryNonSelectException()
                             WindowShowChapters(owner,selectedSerie.selectSeason).open()})
                }
            }
        }
    private fun tryNonSelectException() = selectedSerie.catchNonSelectSeasonException(selectedSerie.selectSeason)
    private fun getSerieTitle(): String? = modelObject.title

}