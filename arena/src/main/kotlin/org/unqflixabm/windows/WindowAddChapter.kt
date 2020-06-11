package org.unqflixabm.windows

import org.unqflixabm.appModels.ChapterAppModel
import org.unqflixabm.appModels.SeasonAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import java.awt.Color

class WindowAddChapter(owner: WindowOwner,chapterAppModel: ChapterAppModel, var seasonAppModel: SeasonAppModel?):
    SimpleWindow<ChapterAppModel>(owner, chapterAppModel) {

    var modelo: ChapterAppModel = modelObject

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

            bindTo("title")
        }

        Label(p0) with {text="Description:"}

        KeyWordTextArea(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            width = 220
            height =80
            bindTo("description")
        }

        Label(p0) with {text="Duration:"}

        NumericField(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            width = 220
            height = 20
            bindTo("duration")
        }

        Label(p0) with {text="Thumbnail:"}

        TextBox(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            width = 220
            height= 20
            bindTo("thumbnail")
        }

        Label(p0) with {text="Video:"}

        TextBox(p0) with {
            color = Color.WHITE
            bgColor = Color.decode("#3A383B")
            background = Color.decode("#3A383B")
            width = 220
            height = 20
            bindTo("video")
        }

        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                background = Color.decode("#3A383B")
                width = 110
                onClick{
                        seasonAppModel!!.addChapter(modelo.newChapterFormat())
                         //resetAddChapter()
                        close()
                       }
            }
            /*
            Button(it) with {
                caption = "Cancel"
                bgColor = Color.decode("#3A383B")
                width = 110
                onClick { resetAddChapter()
                    close() }
            }
            */
        }


    }
    //private fun resetAddChapter() = modelObject.resetAddChapter()
}




