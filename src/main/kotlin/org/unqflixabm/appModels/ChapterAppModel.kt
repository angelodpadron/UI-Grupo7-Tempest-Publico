package org.unqflixabm.appModels

import data.idGenerator
import domain.Chapter
import domain.IdGenerator
import org.uqbar.commons.model.annotations.Observable

@Observable
class ChapterAppModel (private var model: Chapter) {
    val idSystem: IdGenerator = idGenerator
    val id: String
    var title: String
    var description: String
    var duration: Int
    var video: String
    var thumbnail: String

    init {
        this.id = model.id
        this.title = model.title
        this.description = model.description
        this.duration = model.duration
        this.video = model.video
        this.thumbnail = model.thumbnail
    }

    //---------- Modify

    fun updateModel(){
        model.title = title
        model.description = description
        model.duration = duration
        model.video = video
        model.thumbnail = thumbnail
    }

    fun resetModify(){
        this.title = model.title
        this.description = model.description
        this.duration = model.duration
        this.video = model.video
        this.thumbnail = model.thumbnail
    }

    //---------- ViewModel to Model

    fun model():Chapter = model
}
