package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.unqflixabm.appModels.ChapterAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*

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
                    bindContentsTo("duration")
                }
            }

            Panel(p0) with {
                Button(it) with {
                    caption = "Add new chapter"
                    //TODO: onClick
                }
                Button(it) with {
                    caption = "Modify chapter"
                    onClick({tryCatchNonSelectChapterException()
                             WindowModifiedChapter(owner,selectedSeason?.selectChapter).open()})
                }
            }
        }
    private fun tryCatchNonSelectChapterException()= selectedSeason?.catchNonSelectChapterException(selectedSeason?.selectChapter)
}