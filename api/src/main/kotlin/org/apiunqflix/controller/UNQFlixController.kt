package org.apiunqflix.controller

import data.getUNQFlix
import domain.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.apiunqflix.api.TokenJWT
import org.apiunqflix.mapper.*

//excepciones a agregar a carpeta de excepciones

class NotFoundContents (message : String) : Exception (message)

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

        val idContent = ctx.pathParam("contentId")
        try {
            if (idContent.contains("ser")) {
                val content = getSerie(idContent)
                ctx.status(200)
                ctx.json(SerieMapper(
                    content.id,
                    content.title,
                    content.description,
                    content.poster,
                    content.categories.map { CategoryMapper(it.name) } as MutableList<CategoryMapper>,
                    content.relatedContent.map { ContentViewMapper(it.id, it.description, it.title, it.state.toString().contains("Available")) } as MutableList<ContentViewMapper>,
                    content.seasons.map { SeasonMapper(it.id, it.title, it.description, it.poster, it.chapters.map { ChapterMapper(it.id, it.title, it.description, it.duration, it.video, it.thumbnail) } as MutableList<ChapterMapper>) } as MutableList<SeasonMapper>
                )
                )
            } else {
                val content = getMovie(idContent)
                ctx.status(200)
                ctx.json(MovieMapper(
                    content.id,
                    content.title,
                    content.description,
                    content.poster,
                    content.video,
                    content.duration,
                    content.actors,
                    content.directors,
                    content.categories.map { CategoryMapper(it.name) } as MutableList<CategoryMapper>,
                    content.relatedContent.map { ContentViewMapper(it.id, it.description, it.title, it.state.toString().contains("Available")) } as MutableList<ContentViewMapper>
                )
                )
            }
        }
        catch (e : NotFoundException){
            throw NotFoundException("Unknown", "id", idContent)
        }
    }

    fun searchText (ctx: Context){
        ctx.queryParam("text")?: throw BadRequestResponse("Parametro de busqueda vacio")

        val text= ctx.queryParam("text")

        if (text.equals("")) { throw BadRequestResponse("Parametro de busqueda vacio") }

        val foundContents= mutableListOf<ContentViewMapper>()

        try{
            foundContents.addAll( unqFlix.searchMovies(text!!).map { ContentViewMapper(it.id, it.description, it.title, it.state.toString().contains("Available"))})
            foundContents.addAll( unqFlix.searchSeries(text!!).map { ContentViewMapper(it.id, it.description, it.title, it.state.toString().contains("Available"))})
            if (foundContents.isEmpty()){
                throw NotFoundContents("No one content matched with your search")
            }
        }
        catch (e: NotFoundContents){
            throw  NotFoundContents("No one content matched with your search")
        }

        ctx.status(200)
        ctx.json(AvailableContentsMapper(foundContents))
    }


    private fun getMovie(idContent: String): Movie {
        return unqFlix.movies.find { it.id == idContent } ?: throw BadRequestResponse("ID desconcido")
    }
    private fun getSerie(idContent:String):Serie{
        return unqFlix.series.find { it.id == idContent } ?: throw BadRequestResponse("ID desconocido")
    }

}