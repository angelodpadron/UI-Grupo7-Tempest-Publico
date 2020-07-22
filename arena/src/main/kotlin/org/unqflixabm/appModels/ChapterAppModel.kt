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

    //STAGING
    var stageTitle = title
    var stageDescription = description
    var stageDuration = duration
    var stageVideo = video
    var stageThumbnail = thumbnail

    constructor() : this (Chapter("","","",0,"",""))

    //QUERYS

    fun newChapterFormat(): Chapter{
        return Chapter(idGenerator.nextChapterId(),title,description,duration,video,thumbnail)
    }




    fun updateModel(){
        chapter.title = stageTitle
        title = stageTitle

        chapter.description = stageDescription
        description = stageDescription

        chapter.duration = stageDuration
        duration = stageDuration

        chapter.video = stageVideo
        video = stageVideo

        chapter.thumbnail = stageThumbnail
        thumbnail = stageThumbnail

    }

    fun resetModify(){
        this.stageTitle = title
        this.stageDescription = chapter.description
        this.stageDuration = chapter.duration
        this.stageVideo = chapter.video
        this.stageThumbnail = chapter.thumbnail
    }



}
