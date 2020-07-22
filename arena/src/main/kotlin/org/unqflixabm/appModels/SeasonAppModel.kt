package org.unqflixabm.appModels

import data.idGenerator
import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class SeasonAppModel(var season : Season) {

    var id = season.id
    var title = season.title
    var description = season.description
    var poster = season.poster
    var chapters: MutableList<ChapterAppModel> = initChapters()
    var numberOfChapters = chapters.count()

    //STAGING
    var stageTitle = title
    var stageDescription = description
    var stagePoster = poster

    //SELECTIONS

    var selectChapter: ChapterAppModel? = null

    constructor() :this(Season("","","","",chapters = mutableListOf()))

    //INITIATORS

    fun initChapters(): MutableList<ChapterAppModel> {
        return season.chapters.map { ChapterAppModel(it) }.toMutableList()
    }

    //ADDITIONS

    fun addChapter(chapter: Chapter){
        try{
            season.addChapter(chapter)
            chapters = initChapters()
            numberOfChapters = chapters.count()
        }
        catch( e : ExistsException){
            throw UserException(e.message)
        }
    }

    //QUERYS

    fun newSeasonFormat(): Season{
        return Season(idGenerator.nextSeasonId(),title,description,poster,chapters = mutableListOf())
    }

    //EXCEPTIONS

    fun catchNonSelectChapterException(selectChapter: ChapterAppModel?){
        try{
            this.nonSelectChapterException(selectChapter)
        }
        catch(e: NonSelectException){
            throw UserException(e.message)
        }
    }

    fun nonSelectChapterException(selectChapter: ChapterAppModel?){
        if (selectChapter == null) {
            throw NonSelectException("Please select a chapter before continue")
        }
    }



    fun updateModel(){
        season.title = stageTitle
        title = stageTitle

        season.description = stageDescription
        description = stageDescription

        season.poster = stagePoster
        poster = stagePoster

    }

    fun resetModify(){
        stageTitle = title
        stageDescription = description
        stagePoster = poster
    }



}
