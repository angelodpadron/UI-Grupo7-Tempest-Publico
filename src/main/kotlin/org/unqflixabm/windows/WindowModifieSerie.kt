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
        title = "Modify a serie"
        Panel(p0) with {
            asHorizontal()
            TextBox(it) with {
                width = 300
                bindTo("title")
            }
            TextBox(it) with {
                width = 200
                bindTo("poster")
            }
        }

        Panel(p0) with {
            asHorizontal()
            KeyWordTextArea(it) with {
                width = 400
                height = 100
                bindTo("description")
            }
            CheckBox(it) with {
                bindTo("state").setTransformer(StateToBooleanTransformer())
            }

        }

            Panel(p0) with {
                asHorizontal()
                List<CategoryAppModel>(it) with {
                    width = 150
                    height = 200
                    bindItemsTo("categories").adaptWithProp<CategoryAppModel>("categoryName")
                }
                Panel(it) with {
                    asVertical()
                    Button(it) with {
                        caption = "<"
                        //TODO: bindTo
                    }
                    Button(it) with {
                        caption = ">"
                        //TODO: bindTo
                    }
                }
                List<CategoryAppModel>(it) with {
                    var unqflix: UNQflixAppModel
                    width = 150
                    height = 200
                    bindItemsTo("categoriesSyst").adaptWithProp<CategoryAppModel>("categoryName")
                }
            }

            Panel(p0) with {
                asHorizontal()
                KeyWordTextArea(it) with {
                    width = 150
                    height = 200
                    //TODO: habría que printear el contenido existentes, eso es posible?
                }
                Panel(it) with { it1 ->
                    asVertical()
                    Button(it1) with {
                        caption = "<"
                        //TODO: onClick
                    }
                    Button(it1) with {
                        caption = ">"
                        //TODO: onClick
                    }
                }
                List<ContentAppModel>(it) with {
                    width = 150
                    height = 200
                    //TODO: tienen que aparecer todas las opciones de contenidos
                }
            }
            Panel(p0) with {
                asHorizontal()
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
