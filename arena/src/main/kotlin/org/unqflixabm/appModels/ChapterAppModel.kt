package org.unqflixabm.appModels

import data.idGenerator
import domain.Chapter
import domain.IdGenerator
import org.uqbar.commons.model.annotations.Observable

@Observable
class ChapterAppModel (var chapter: Chapter){

    var id = chapter.id
    var title = chapter.title
    var description = chapter.description
    var duration = chapter.duration
    var video = chapter.video
    var thumbnail = chapter.thumbnail

    init {
        id
        title
        description
        duration
        video
        thumbnail
    }

    constructor() : this (Chapter("","","",0,"",""))

    //QUERYS

    fun newChapterFormat(): Chapter{
        return Chapter(idGenerator.nextChapterId(),title,description,duration,video,thumbnail)
    }
    /*---------- Modify

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

     */

    //---------- ViewModel to Model
}
