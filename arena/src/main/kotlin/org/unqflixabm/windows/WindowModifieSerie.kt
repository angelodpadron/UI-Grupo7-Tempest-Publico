package org.unqflixabm.windows

import org.unqflixabm.appModels.CategoryAppModel
import org.unqflixabm.appModels.ContentAppModel
import org.unqflixabm.appModels.SeriesAppModel
import org.unqflixabm.appModels.UNQflixAppModel
import org.unqflixabm.transformers.StateToBooleanTransformer
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List

class WindowModifieSerie(owner: WindowOwner, model: SeriesAppModel?,system: UNQflixAppModel):
    SimpleWindow<SeriesAppModel>(owner,model ) {

    var modelo : SeriesAppModel = modelObject

    var sistema : UNQflixAppModel = system

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "Modify a serie"
        Panel(p0) with {
            asHorizontal()
            Label(it) with {text = "Title"}
            TextBox(it) with {
                title = "Title"
                width = 200
                bindTo("stageTitle")
            }
            Label(it) with {text = "Poster"}
            TextBox(it) with {
                title = "Poster"
                width = 150
                bindTo("stagePoster")
            }
        }

        Panel(p0) with {
            Label(it) with {
                text = "Description"
                align = "left"
            }
        }

        Panel(p0) with {
            asHorizontal()
            KeyWordTextArea(it) with {
                width = 400
                height = 100
                bindTo("stageDescription")

            }
        }

        Panel(p0) with {
            asHorizontal()
            Label(it) with {text = "Availability"}
            CheckBox(it) with {
                bindTo("stageState")//.setTransformer(StateToBooleanTransformer())
            }
        }

        //CATEGORY PANEL

        Panel(p0) with {
            asHorizontal()
            List<CategoryAppModel>(it) with {
                width = 150
                height = 100
                bindItemsTo("stageCategories").adaptWithProp<CategoryAppModel>("categoryName")
                bindSelectedTo("selectCategorySerie")
            }
            Panel(it) with {
                asVertical()
                Button(it) with {
                    caption = "<"
                    onClick { addCategory() }
                }
                Button(it) with {
                    caption = ">"
                    onClick { deleteCategory()}
                }
            }
            List<CategoryAppModel>(it) with {
                width = 150
                height = 100
                bindItems(ObservableProperty<UNQflixAppModel>(sistema,"categories")).adaptWithProp<CategoryAppModel>("categoryName")
                bindSelectedTo("selectCategorySerie")
            }
        }
        Panel(p0) with{
            Label(it) with {
                text = "Related content"
                align = "left"
            }
        }

        //CONTENT PANEL

        Panel(p0) with {
            asHorizontal()
            List<ContentAppModel>(it) with {
                width = 150
                height = 100
                bindItemsTo("relatedContent").adaptWithProp<ContentAppModel>("contentDescription")
                bindSelectedTo("selectContentSerie")
            }
            Panel(it) with {
                asVertical()
                Button(it) with {
                    caption = "<"
                    onClick { addContent() }
                }
                Button(it) with {
                    caption = ">"
                    onClick { deleteContent() }
                }
            }
            List<ContentAppModel>(it) with {
                width = 150
                height = 100
                bindItems(ObservableProperty<UNQflixAppModel>(sistema,"contents")).adaptWithProp<ContentAppModel>("contentDescription")
                bindSelectedTo("selectContentSerie")
            }
        }

        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick {
                    updateModel()
                    close()}
            }

            Button(it) with {
                caption = "Cancel"
                onClick {
                    resetModify()
                    close() }
            }

        }
    }
    private fun addCategory() = modelo.stageCategories(modelo.selectCategorySerie!!)
    private fun deleteCategory() = modelo.removeCategory (modelo.selectCategorySerie!!)
    private fun addContent() = modelo.addContent(modelo.selectContentSerie!!)
    private fun deleteContent() = modelo.removeContent(modelo.selectContentSerie!!)
    private fun resetModify() = modelObject.resetModify()
    private fun updateModel() = modelObject.updateModel()

}
