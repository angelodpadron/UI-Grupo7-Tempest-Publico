package org.unqflixabm.appModels

import domain.Chapter
import domain.ExistsException
import domain.Season
import domain.Serie
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class SeasonAppModel(private var model: Season) {

    var system: Season = model
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var poster: String = ""
    var chapters: MutableList<ChapterAppModel> = mutableListOf()
    var numberOfChapters: Int
    var selectChapter: ChapterAppModel? = null
    //Required Fields To add a new Chapter
    var titleChapter: String = ""
    var descriptionChapter: String = ""
    var durationChapter: Int = 0
    var thumbNailChapter: String = ""
    var videoChapter: String = ""
    //

    init {
        this.id = model.id
        this.title = model.title
        this.description = model.description
        this.poster = model.poster
        this.chapters = initChapters()
        this.numberOfChapters = this.chapters.count()
    }

    //INITIATORS

    fun initChapters(): MutableList<ChapterAppModel> {
        return model.chapters.map { ChapterAppModel(it) }.toMutableList()
    }

    //TO MODEL

    fun model(): Season = model

    //ADDS

    fun newChapter():Chapter{
        return Chapter(getNextChapterId(),titleChapter,descriptionChapter,durationChapter,videoChapter,thumbNailChapter)
    }
    fun addChapter(){
        try{
            //agregar al modelo
            system.addChapter(newChapter())
            //update viewmodel
            chapters = initChapters()
            numberOfChapters = this.chapters.count()
        }
        catch( e : ExistsException){
            throw UserException(e.message)
        }
    }

    //EXCEPTIONS

    /*
    @Function  control that a chapter is selected before interact with him
     */
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

    //QUERYS

    fun getNextChapterId():String {
        val lastChapterId :String = this.chapters.last().id

        return "cha_${(lastChapterId.split("_").last()).toInt()+1}"
    }
}
