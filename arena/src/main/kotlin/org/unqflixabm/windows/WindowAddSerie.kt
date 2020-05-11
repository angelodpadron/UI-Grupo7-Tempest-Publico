package org.unqflixabm.windows

import domain.Content
import org.unqflixabm.appModels.CategoryAppModel
import org.unqflixabm.appModels.ContentAppModel
import org.unqflixabm.appModels.UNQflixAppModel
import org.unqflixabm.transformers.StateToBooleanTransformer
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List

class WindowAddSerie(owner: WindowOwner, unqflixAppModel: UNQflixAppModel):
    SimpleWindow<UNQflixAppModel>(owner, unqflixAppModel) {

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title ="Add a new Serie"
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
            asVertical()
            KeyWordTextArea(it) with {
                title = "Description:"
                width = 400
                height = 100
                bindTo("description")
            }
        }

        Panel(p0) with {
            asHorizontal()
            Label(it) with {text = "Availability"}
            CheckBox(it) with {
                title = "State"
                bindTo("stateSerie").setTransformer(StateToBooleanTransformer())
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
                bindItemsTo("categoriesSerie").adaptWithProp<CategoryAppModel>("categoryName")
                bindSelectedTo("selectCategoryDom")
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
                bindItemsTo("categories").adaptWithProp<CategoryAppModel>("categoryName")
                bindSelectedTo("selectCategoryVm")
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
            List<Content>(it) with {
                width = 150
                height = 100
                bindItemsTo("relatedContentSerie").adaptWithProp<ContentAppModel>("contentDescription")
                bindSelectedTo("selectContentDom")
            }
            Panel(it) with {
                asVertical()
                Button(it) with {
                    caption = "<"
                    onClick { addContent() }
                }
                Button(it) with {
                    caption = ">"
                    onClick {deleteContent()}
                }
            }
            List<ContentAppModel>(it) with {
                var unqflix: UNQflixAppModel
                width = 150
                height = 100
                bindItemsTo("contents").adaptWithProp<ContentAppModel>("contentDescription")
                bindSelectedTo("selectContentVm")
            }
        }

        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick {addNewSerie()
                    close()}
            }

            Button(it) with {
                caption = "Cancel"
                onClick { close() }
            }
        }
    }
    private fun deleteCategory() = modelObject .removeCategory (modelObject.selectCategoryDom)
    private fun addCategory() = modelObject.addSerieCategory(modelObject.selectCategoryVm)
    private fun addNewSerie() = modelObject.addSerie()
    private fun deleteContent() = modelObject.removeContent(modelObject.selectContentDom)
    private fun addContent() = modelObject.addSerieContent(modelObject.selectContentVm)
}