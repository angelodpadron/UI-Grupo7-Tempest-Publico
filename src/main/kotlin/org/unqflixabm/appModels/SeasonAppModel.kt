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

    init {
        this.id = model.id
        this.title = model.title
        this.description = model.description
        this.poster = model.poster
        this.chapters = initChapters(model)
        this.numberOfChapters = this.chapters.count()
    }

    fun initChapters(season: Season): MutableList<ChapterAppModel> {
        return season.chapters.map { ChapterAppModel(it) }.toMutableList()
    }

    //To Model
    fun model(): Season = model

    private fun toChapter(chapterAppModel: ChapterAppModel): Chapter {
        return Chapter(
            chapterAppModel.id,
            chapterAppModel.title,
            chapterAppModel.description,
            chapterAppModel.duration,
            chapterAppModel.video,
            chapterAppModel.thumbnail
        )
    }
    //Querys

    fun addChapter(chapterAppModel: ChapterAppModel){
        //agregar al modelo
        system.addChapter(chapterAppModel.model())
        //update viewmodel
        this.initChapters(model)
    }

    //Exceptions

    fun catchExistChapterException(chapter: ChapterAppModel){
        var season : SeasonAppModel = this
        try{
            season.addChapter(chapter)
        }
        catch( e : ExistsException){
            UserException(e.message)
        }
    }

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
