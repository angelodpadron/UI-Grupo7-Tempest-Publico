package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.unqflixabm.appModels.ChapterAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import java.awt.Color

class WindowShowChapters(owner: WindowOwner, seasonAppModel: SeasonAppModel?):
    SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {

    var selectedSeason: SeasonAppModel? =modelObject

    override fun addActions(p0: Panel?) {
        }

        override fun createFormPanel(p0: Panel) {
            Label(p0) with {
                text = "Season:" + modelObject.title
            }

            table<ChapterAppModel>(p0) {
                title = "Chapters:"
                visibleRows = 10
                bindItemsTo("chapters")
                bindSelectionTo("selectChapter")
                column {
                    title = "#"
                    fixedSize = 75
                    bindContentsTo("id") //TODO: probablemente haya que sacarlo
                }
                column {
                    title = "Title"
                    fixedSize = 300
                    bindContentsTo("title")
                }
                column {
                    title = "Duration"
                    fixedSize = 75
                    background = Color.decode("#3A383B")
                    color = Color.WHITE
                    bindContentsTo("duration")
                }
            }

            Panel(p0) with {
                asHorizontal()
                Button(it) with {
                    caption = "Add new chapter"
                    bgColor = Color.decode("#580AAF")
                    width = 200

                    onClick({WindowAddChapter(owner,selectedSeason).open()})
                }
                Button(it) with {
                    bgColor = Color.decode("#580AAF")
                    width = 200
                    caption = "Modify chapter"
                    onClick({tryCatchNonSelectChapterException()
                             WindowModifiedChapter(owner,selectedSeason?.selectChapter).open()})
                }
            }
        }
    private fun tryCatchNonSelectChapterException()= selectedSeason?.catchNonSelectChapterException(selectedSeason?.selectChapter)
}