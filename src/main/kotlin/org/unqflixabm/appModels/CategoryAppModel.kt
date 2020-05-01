package org.unqflixabm.appModels

import domain.Category

class CategoryAppModel(var category: Category){

    var id: String = ""
    var name: String = ""

    init {
        this.id = category.id
        this.name = category.name
    }

}
