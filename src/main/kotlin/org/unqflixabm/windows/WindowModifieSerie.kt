package org.unqflixabm.windows

import org.eclipse.swt.widgets.Table
import org.unqflixabm.appModels.CategoryAppModel
import org.unqflixabm.appModels.ContentAppModel
import org.unqflixabm.appModels.SeriesAppModel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List

class WindowModifieSerie (owner: WindowOwner, serieAppModel: SeriesAppModel):
    SimpleWindow<SeriesAppModel>(owner, serieAppModel){

        override fun addActions(p0: Panel?) {
        }

        override fun createFormPanel(p0: Panel) {

            Panel(p0) with {
                asHorizontal()
                TextBox(it) with {
                    title = "Title"
                    bindTo("title")
                }
                TextBox(it) with {
                    title = "Poster"
                    bindTo("poster")
                }
            }

            Panel(p0) with {
                asHorizontal()
                KeyWordTextArea(it) with {
                    title = "Description:"
                    bindTo("description")
                }
                CheckBox(it) with {
                    title = "State"
                    bindTo("state") //TODO: hay que lograr que defina states
                }
            }

            Panel(p0) with {
                asColumns(3)
                KeyWordTextArea(it) with {
                    title = "Categories:"
                    //TODO: habría que printear las categorías existentes, eso es posible?
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
                    //TODO: tienen que aparecer todas las opciones de categorías
                }
            }

            Panel(p0) with {
                asColumns(3)
                KeyWordTextArea(it) with {
                    title = "Related content:"
                    //TODO: habría que printear el contenido existentes, eso es posible?
                }
                Panel(it) with {
                    asVertical()
                    Button(it) with {
                        caption = "<"
                        //TODO: onClick
                    }
                    Button(it) with {
                        caption = ">"
                        //TODO: onClick
                    }
                }
                List<ContentAppModel>(it) with {
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