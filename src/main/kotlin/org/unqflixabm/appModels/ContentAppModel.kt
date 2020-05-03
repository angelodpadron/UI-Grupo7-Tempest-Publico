package org.unqflixabm.appModels

import domain.Content

class ContentAppModel (model: Content){

    var id : String = ""
    var title : String = ""

    init {
        id = model.id
        title = model.title
    }

}
