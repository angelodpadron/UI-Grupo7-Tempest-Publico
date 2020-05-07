package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import java.awt.Color

class WindowAddChapter(owner: WindowOwner, seasonAppModel: SeasonAppModel?):
    SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {
    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title= "Add a new Chapter"

        Label(p0) with {text="Title:"}

        TextBox(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            width = 220
            height =20

            bindTo("titleChapter")
        }

        Label(p0) with {text="Description:"}

        KeyWordTextArea(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            width = 220
            height =80
            bindTo("descriptionChapter")
        }

        Label(p0) with {text="Duration:"}

        NumericField(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            width = 220
            height = 20
            bindTo("durationChapter")
        }

        Label(p0) with {text="Thumbnail:"}

        TextBox(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            width = 220
            height= 20
            bindTo("thumbNailChapter")
        }

        Label(p0) with {text="Video:"}

        TextBox(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            background = Color.decode("#3A383B")
            width = 220
            height = 20
            bindTo("videoChapter")
        }

        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                background = Color.decode("#3A383B")
                width = 110
                onClick({addChapter()
                         close() })
            }
            Button(it) with {
                caption = "Cancel"
                bgColor = Color.decode("#3A383B")
                width = 110
                onClick({ close() })
            }
        }
    }
    private fun addChapter() = modelObject.addChapter()
}




