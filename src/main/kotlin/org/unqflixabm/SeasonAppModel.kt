package org.unqflixabm

import domain.Chapter
import domain.Season


class SeasonAppModel(var season: Season){
    val id: String
    var title: String
    var description: String
    var poster: String
    var chapters: MutableList<ChapterAppModel>

    init{
        this.id = season.id
        this.title = season.title
        this.description = season.description
        this.poster = season.poster
        this.chapters = initChapters(season)
    }

    fun initChapters (season: Season): MutableList<ChapterAppModel> {
        return season.chapters.map{ChapterAppModel(it)}.toMutableList()
    }

    //agregar excepcion , no se pueden agregar dos veces el mismo capitulo.
    fun addChapter(chapter: ChapterAppModel) =chapters.add(chapter)
    
    fun deleteChapter(idChapter: String) = chapters.removeIf { it.id == idChapter }
}
