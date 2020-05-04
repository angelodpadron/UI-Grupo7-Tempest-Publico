package org.unqflixabm.appModels

import domain.Category
import org.uqbar.commons.model.annotations.Observable

@Observable
class CategoryAppModel(var category: Category){

    var id: String = ""
    var name: String = ""

    init {
        this.id = category.id
        this.name = category.name
    }

    fun toModel(): Category = category
    fun categoryName() = "$name"
}
