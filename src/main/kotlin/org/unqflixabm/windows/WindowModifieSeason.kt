package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*

class WindowModifieSeason (owner: WindowOwner, seasonAppModel: SeasonAppModel):
    SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {
        override fun addActions(p0: Panel?) {
        }

        override fun createFormPanel(p0: Panel) {
            TextBox(p0) with {
                title = "Title:"
                bindTo("title")
            }

            KeyWordTextArea(p0) with {
                title = "Description:"
                bindTo("description")
            }

            TextBox(p0) with {
                title = "Poster:"
                bindTo("poster")
            }

            Panel(p0) with {
                Button(it) with {
                    caption = "Accept"
                    //TODO: onClick
                }
                Button(it) with {
                    caption = "Cancel"
                    //TODO: onClick
                }
            }
        }
    }