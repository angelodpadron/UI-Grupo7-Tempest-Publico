package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.unqflixabm.appModels.SeriesAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import java.awt.Color

class WindowAddSeason(owner: WindowOwner, seasonAppModel: SeasonAppModel,var seriesAppModel: SeriesAppModel):
    SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {

    var modelo : SeasonAppModel = modelObject
    var sistema : SeriesAppModel = seriesAppModel

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "Add a new season"

        Label(p0) with {text="Title :"}

        TextBox(p0) with {
            width = 220
            height = 20
            bindTo("title")
        }

        Label(p0) with {text="Description:"}

        KeyWordTextArea(p0) with {
            width = 220
            height = 20
            bindTo("description")
        }

        Label(p0) with {text="Poster:"}

        TextBox(p0) with {
            width = 220
            height = 20
            bindTo("poster")
        }

        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                width = 110
                height = 30
                bgColor = Color.decode("#870AAE")
                onClick {
                         sistema.addSeason(modelo.newSeasonFormat())
                         //resetAddSeason()
                         close()
                         }
            }
            /*Button(it) with {
                width = 110
                height = 30
                caption = "Cancel"
                bgColor = Color.decode("#870AAE")
                onClick {
                         resetAddSeason()
                         close()
                        }
            }
             */
        }
    }
    //private fun resetAddSeason() = modelObject.resetAddSeason()
}
