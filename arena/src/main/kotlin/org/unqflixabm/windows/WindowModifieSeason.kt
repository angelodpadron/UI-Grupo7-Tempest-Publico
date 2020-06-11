package org.unqflixabm.windows

import org.unqflixabm.appModels.SeasonAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import java.awt.Color

class WindowModifieSeason(owner: WindowOwner, seasonAppModel: SeasonAppModel?):
    SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {
        override fun addActions(p0: Panel?) {
        }

        override fun createFormPanel(p0: Panel) {
        /*
            TextBox(p0) with {
                bindTo("title")
            }

            Label(p0) with {text="Description:"}

            KeyWordTextArea(p0) with {
                bindTo("description")
                width = 400
                height = 100

            }

            Label(p0) with {text="Poster:"}

            TextBox(p0) with {
                bindTo("poster")
            }

            Panel(p0) with {
                asHorizontal()
                Button(it) with {
                    caption = "Accept"
                    bgColor = Color.decode("#580AAF")
                    width = 200
                    onClick{
                        updateModel()
                        close()
                    }
                }
                Button(it) with {
                    caption = "Cancel"
                    bgColor = Color.decode("#580AAF")
                    width = 200
                    onClick{
                        resetModify()
                        close()
                    }
                }
            }

         */
        }


    //fun updateModel() = modelObject.updateModel()
    // fun resetModify() = modelObject.resetModify()


}