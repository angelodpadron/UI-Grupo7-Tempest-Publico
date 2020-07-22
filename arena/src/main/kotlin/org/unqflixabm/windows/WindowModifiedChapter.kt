package org.unqflixabm.windows

import org.unqflixabm.appModels.ChapterAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*

class WindowModifiedChapter(owner: WindowOwner, chapterAppModel: ChapterAppModel?):
    SimpleWindow<ChapterAppModel>(owner, chapterAppModel) {
        override fun addActions(p0: Panel?) {
        }

        override fun createFormPanel(p0: Panel) {

            Panel(p0) with {
                asVertical()
                Label(p0) with { text = "Title" }
                TextBox(p0) with {
                    title = "Title:"
                    width = 200
                    bindTo("stageTitle")
                }
                Label(p0) with { text = "Description" }
                KeyWordTextArea(p0) with {
                    title = "Description:"
                    width = 200
                    height = 200
                    bindTo("stageDescription")
                }
                Label(p0) with { text = "Duration" }
                NumericField(p0) with {
                    title = "Duration:"
                    width = 200
                    bindTo("stageDuration")
                }
                Label(p0) with { text = "Thumbnail" }
                TextBox(p0) with {
                    title = "Thumbnail:"
                    width = 200
                    bindTo("stageThumbnail")
                }
                Label(p0) with { text = "Video" }
                TextBox(p0) with {
                    title = "Video:"
                    width = 200
                    bindTo("stageVideo")
                }
            }

            Panel(p0) with {
                asHorizontal()
                Button(it) with {
                    caption = "Accept"
                    onClick {
                        updateModel()
                        close()
                    }
                }
                Button(it) with {
                    caption = "Cancel"
                    onClick {
                        resetModify()
                        close()}
                }
            }


        }
    private fun updateModel() = modelObject.updateModel()
    private fun resetModify() = modelObject.resetModify()
}