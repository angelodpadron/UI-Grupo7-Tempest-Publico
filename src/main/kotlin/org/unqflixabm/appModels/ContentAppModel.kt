package org.unqflixabm.appModels

import domain.Content
import org.uqbar.commons.model.annotations.Observable

@Observable

class ContentAppModel (var model: Content){

    var id : String = ""
    var title : String = ""

    init {
        id = model.id
        title = model.title
    }

    //TO MODEL

    fun toModel() = model

    //ADAPTERS

    fun contentDescription() = "$id - $title"

}