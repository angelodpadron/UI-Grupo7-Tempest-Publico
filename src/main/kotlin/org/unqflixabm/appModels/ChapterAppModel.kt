package org.unqflixabm.appModels

import domain.Chapter
import org.uqbar.commons.model.annotations.Observable

@Observable
class ChapterAppModel (private var model: Chapter) {
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

    fun model():Chapter = model
}
