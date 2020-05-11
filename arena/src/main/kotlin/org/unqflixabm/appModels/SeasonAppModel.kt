package org.unqflixabm.appModels

import data.idGenerator
import domain.*
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class SeasonAppModel(var model: Season) {

    val idSystem: IdGenerator = idGenerator
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var poster: String = ""
    var chapters: MutableList<ChapterAppModel> = mutableListOf()
    var numberOfChapters: Int
    var selectChapter: ChapterAppModel? = null

    //----------Required Fields To add a new Chapter
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

    fun updateModel(){
        model.title = title
        model.description = description
        model.poster = poster
    }

    fun resetModify(){
        title = model.title
        description = model.description
        poster = model.poster
    }

    //----------SetUp

    fun initChapters(): MutableList<ChapterAppModel> {
        return model.chapters.map { ChapterAppModel(it) }.toMutableList()
    }

    //----------ViewModel to Model

    fun model(): Season = model

    //----------Adds

    fun newChapter():Chapter{
        return Chapter(idSystem.nextChapterId(), titleChapter,descriptionChapter,durationChapter,videoChapter,thumbNailChapter)
    }
    fun addChapter(){
        try{
            //agregar al modelo
            model.addChapter(newChapter())
            //update viewmodel
            chapters = initChapters()
            numberOfChapters = this.chapters.count()
        }
        catch( e : ExistsException){
            throw UserException(e.message)
        }
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

}
