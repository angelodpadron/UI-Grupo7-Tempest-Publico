package org.unqflixabm

import domain.Chapter

class ChapterAppModel (chapter: Chapter) {
    val id: String
    var title: String
    var description: String
    var duration: Int
    var video: String
    var thumbnail: String

    init {
        this.id = chapter.id
        this.title = chapter.title
        this.description = chapter.description
        this.duration = chapter.duration
        this.video = chapter.video
        this.thumbnail = chapter.thumbnail
    }
}