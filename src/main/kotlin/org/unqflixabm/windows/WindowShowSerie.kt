package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.unqflixabm.appModels.SeriesAppModel
import org.uqbar.arena.widgets.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.windows.SimpleWindow
import java.awt.Color

class WindowShowSerie(owner: WindowOwner, seriesAppModel: SeriesAppModel?):
    SimpleWindow<SeriesAppModel>(owner, seriesAppModel){

    var modelo : SeriesAppModel = modelObject

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
                visibleRows = 6
                bindItemsTo("seasons")
                bindSelectionTo("selectSeason")
                column {
                    title = "#"
                    fixedSize = 150
                    bindContentsTo("id")
                }
                column {
                    title = "Title"
                    fixedSize = 300
                    bindContentsTo("title")
                }
                column {
                    title = "#Chapters"
                    fixedSize = 150
                    bindContentsTo("numberOfChapters")
                }
            }

            Panel(p0) with {
                asHorizontal()
                Button(it) with {
                    caption = "Add new season"
                    bgColor = Color.decode("#580AAF")
                    width = 200
                    onClick({WindowAddSeason(owner,modelo).open()})
                }
                Button(it) with {
                    caption = "Modify season"
                    bgColor = Color.decode("#580AAF")
                    width = 200
                    onClick({tryNonSelectException()
                             WindowModifieSeason(owner,selectedSerie.selectSeason).open()})
                }
                Button(it) with {
                    caption = "Show chapters"
                    bgColor = Color.decode("#580AAF")
                    width = 200
                    onClick({tryNonSelectException()
                             WindowShowChapters(owner,selectedSerie.selectSeason).open()})
                }
            }
        }
    private fun tryNonSelectException() = selectedSerie.catchNonSelectSeasonException(selectedSerie.selectSeason)
    private fun getSerieTitle(): String? = modelObject.title

}