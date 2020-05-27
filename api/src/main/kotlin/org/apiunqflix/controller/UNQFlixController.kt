package org.apiunqflix.controller

import data.getUNQFlix
import domain.*
import io.javalin.http.Context
import org.apiunqflix.api.TokenJWT
import org.apiunqflix.mapper.*

class UNQFlixController(val unqFlix: UNQFlix,val token: TokenJWT) {

    fun getAvailableContent(ctx: Context) {

        var contents : MutableList<ContentViewMapper> = mutableListOf()

        for (serie in unqFlix.series) {
            if (serie.state.toString().contains("Available")) {
                contents.add(ContentViewMapper(serie.id, serie.description, serie.title, true))
            }
        }
        for (movie in unqFlix.movies) {
            if (movie.state.toString().contains("Available")) {
                contents.add(ContentViewMapper(movie.id, movie.description, movie.title, true))
            }
        }

        contents.sortBy { it.title }

        ctx.status(200)
        ctx.json(AvailableContentsMapper(contents))
    }


    fun getAllBanners(ctx: Context) {

        var banners : MutableList<BannerMapper> = mutableListOf()

        for (serie in unqFlix.series) {
            banners.add(BannerMapper(serie.id, serie.title, serie.poster))
        }
        for (movie in unqFlix.movies) {
            banners.add(BannerMapper(movie.id, movie.title, movie.poster))
        }

        banners.sortBy { it.title }

        ctx.status(200)
        ctx.json(AllBannersMapper(banners))
    }


    fun getContentById(ctx: Context) {
        val contentID : String = ctx.bodyAsClass(String::class.java)

        var selectedContent : Content? = null
        var isSerie = false
        var isMovie = false

        for (serie in unqFlix.series) {
            if (serie.id == contentID) {
                selectedContent = serie
                isSerie = true
            }
        }
        if (selectedContent == null) {
            for (movie in unqFlix.movies) {
                if (movie.id == contentID) {
                    selectedContent = movie
                    isMovie = true
                }
            }
        }

        if (selectedContent != null) {
            if (isMovie) {
                //TODO: armar .json usando movieToMovieMapper
            }
            if (isSerie) {
                //TODO: armar .json usando serieToSerieMapper
            }
        } else {
            //TODO: mensaje de error, "no se encontró contenido con ese id"
        }
    }


    // Funciones auxiliares

    private fun serieToSerieMapper (serie: Serie): SerieMapper {
        var mappedCategories : MutableList<CategoryMapper> = mutableListOf()
        var mappedSeasons : MutableList<SeasonMapper> = mutableListOf()
        var mappedRelatedContent : MutableList<ContentViewMapper> = mutableListOf()

        for (category in serie.categories) {
            mappedCategories.add(CategoryMapper(category.name))
        }
        for (season in serie.seasons) {
            mappedSeasons.add(seasonToSeasonMapper(season))
        }
        for (content in serie.relatedContent) {
            mappedRelatedContent.add(ContentViewMapper(content.id, content.description, content.title, content.state.toString().contains("Available")))
        }

        return (SerieMapper(serie.id, serie.title, serie.description, serie.poster, mappedCategories, mappedRelatedContent, mappedSeasons))
    }


    private fun seasonToSeasonMapper (season: Season): SeasonMapper {
        var mappedChapters : MutableList<ChapterMapper> = mutableListOf()

        for (chapter in season.chapters) {
            mappedChapters.add(ChapterMapper(chapter.id, chapter.title, chapter.description, chapter.duration, chapter.video, chapter.thumbnail))
        }

        return (SeasonMapper(season.id, season.title, season.description, season.poster, mappedChapters))
    }

    
    private fun movieToMovieMapper (movie: Movie): MovieMapper {
        var mappedCategories : MutableList<CategoryMapper> = mutableListOf()
        var mappedRelatedContent : MutableList<ContentViewMapper> = mutableListOf()

        for (category in movie.categories) {
            mappedCategories.add(CategoryMapper(category.name))
        }
        for (content in movie.relatedContent) {
            mappedRelatedContent.add(ContentViewMapper(content.id, content.description, content.title, content.state.toString().contains("Available")))
        }

        return MovieMapper(movie.id, movie.title, movie.description, movie.poster, movie.video, movie.duration, movie.actors, movie.directors, mappedCategories, mappedRelatedContent)
    }

}