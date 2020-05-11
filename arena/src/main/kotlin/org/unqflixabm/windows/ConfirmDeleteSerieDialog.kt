package org.unqflixabm.windows

import org.unqflixabm.appModels.UNQflixAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class ConfirmDeleteSerieDialog (owner :WindowOwner, model: UNQflixAppModel):Dialog<UNQflixAppModel>(owner,model){

    override fun createFormPanel(mainPanel: Panel?) {
        Label(mainPanel) with {

            text = "Confirm delete of " + getSerieTitle()
        }
        Button(mainPanel)with{
            caption= "Accept"
            background = Color.decode("#580AAF")
            width = 110
            onClick { deleteSerie()
                close() }
        }
        Button(mainPanel)with{
            caption= "Cancel"
            background = Color.decode("#580AAF")
            width = 110
            onClick {close()}
        }
    }

    private fun  deleteSerie() {
        modelObject.deleteSerie(modelObject.selectSerie)
    }
    private fun getSerieTitle(): String?{
        return (modelObject.selectSerie?.title)
    }
}