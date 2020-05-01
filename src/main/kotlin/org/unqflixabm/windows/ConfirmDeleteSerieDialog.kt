package org.unqflixabm.windows

import org.unqflixabm.appModel.SeriesAppModel
import org.unqflixabm.appModel.UNQflixAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class ConfirmDeleteSerieDialog (owner :WindowOwner, model: UNQflixAppModel):Dialog<UNQflixAppModel>(owner,model){

    override fun createFormPanel(mainPanel: Panel?) {
        Label(mainPanel) with {

            text = "Confirm delete of "+(getSerieTitle())
        }
        Button(mainPanel)with{
            caption= "Accept"
            onClick({ deleteSerie()
                      close() })
        }
        Button(mainPanel)with{
            caption= "Cancel"
            onClick({close()})
        }
    }

    fun  deleteSerie() {
        modelObject.deleteSerie(modelObject.selectSerie)
    }
    fun getSerieTitle(): SeriesAppModel?{
        return modelObject.getSerie(modelObject.selectSerie)
    }
}