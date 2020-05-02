package org.unqflixabm.appModels

import domain.Chapter
import domain.Season
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class SeasonAppModel(var season: Season) {
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var poster: String = ""
    var chapters: MutableList<ChapterAppModel> = mutableListOf()
    var numberOfChapters: Int
    var selectChapter: ChapterAppModel? = null

    init {
        this.id = season.id
        this.title = season.title
        this.description = season.description
        this.poster = season.poster
        this.chapters = initChapters(season)
        this.numberOfChapters = this.chapters.count()
    }

    fun initChapters(season: Season): MutableList<ChapterAppModel> {
        return season.chapters.map { ChapterAppModel(it) }.toMutableList()
    }

    fun addChapter(chapter: ChapterAppModel) = season.addChapter(toChapter(chapter))

    fun deleteChapter(input: String) = season.deleteChapter(input)

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
