package org.unqflixabm.windows

import org.unqflixabm.appModels.CategoryAppModel
import org.unqflixabm.appModels.ContentAppModel
import org.unqflixabm.appModels.SeriesAppModel
import org.unqflixabm.appModels.UNQflixAppModel
import org.unqflixabm.transformers.StateToBooleanTransformer
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List

class WindowModifieSerie(owner: WindowOwner, model: SeriesAppModel?):
    SimpleWindow<SeriesAppModel>(owner,model ) {
    var serie: SeriesAppModel = modelObject

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        /*   title = "Modify a serie"
        Panel(p0) with {
            asHorizontal()
            Label(it) with {text = "Title"}
            TextBox(it) with {
                title = "Title"
                width = 200
                bindTo("title")
            }
            Label(it) with {text = "Poster"}
            TextBox(it) with {
                title = "Poster"
                width = 150
                bindTo("poster")
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
                bindTo("description")

            }
        }

        Panel(p0) with {
            asHorizontal()
            Label(it) with {text = "Availability"}
            CheckBox(it) with {
                bindTo("state").setTransformer(StateToBooleanTransformer())
            }
        }

        Panel(p0) with{
            Label(it) with {
                text = "Categories"
                align = "left"
            }
        }

        Panel(p0) with {
            asHorizontal()
            List<CategoryAppModel>(it) with {
                width = 150
                height = 100
                bindItemsTo("categories").adaptWithProp<CategoryAppModel>("categoryName")

                bindSelectedTo("selectCategorySerie")
            }
            Panel(it) with {
                asVertical()
                Button(it) with {
                    caption = "<"
                    onClick {modifyCategoriesSerie()}
                }
                Button(it) with {
                    caption = ">"
                    onClick {modifyDeleteCategories()}
                }
            }
            List<CategoryAppModel>(it) with {
                var unqflix: UNQflixAppModel
                width = 150
                height = 100
                bindSelectedTo("selectCategorySerie")
                bindItemsTo("categoriesSyst").adaptWithProp<CategoryAppModel>("categoryName")
            }
        }

        Panel(p0) with{
            Label(it) with {
                text = "Related content"
                align = "left"
            }
        }

        Panel(p0) with {
            asHorizontal()
            KeyWordTextArea(it) with {
                width = 150
                height = 100
                //TODO: habría que printear el contenido existentes, eso es posible?
            }
            Panel(it) with { it1 ->
                asVertical()
                Button(it1) with {
                    caption = "<"
                }
                Button(it1) with {
                    caption = ">"
                }
            }
            List<ContentAppModel>(it) with {
                width = 150
                height = 100
                //TODO: tienen que aparecer todas las opciones de contenidos
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
                    close()
                }
            }
        }
    }
    private fun resetModify() = modelObject.resetModify()
    private fun updateModel() = modelObject.updateModel()
    private fun modifyCategoriesSerie()=modelObject.addCategory(modelObject.selectCategorySerie )
    private fun modifyDeleteCategories() = modelObject.modifydeleteCategories(modelObject.selectCategorySerie)
    */
    }
}
