package org.apiunqflix.controller

import data.getUNQFlix
import domain.Available
import domain.Category
import domain.UNQFlix
import io.javalin.http.Context
import org.apiunqflix.api.TokenJWT
import org.apiunqflix.mapper.*

class UNQFlixController(val unqFlix: UNQFlix,val token: TokenJWT) {

    fun getAvailableContent(ctx: Context) {

        var contents : MutableList<ContentMapper> = mutableListOf()

        for (serie in unqFlix.series) {
            if (serie.state.toString().contains("Available")) {
                contents.add(ContentMapper(serie.id, serie.description, serie.title, true))
            }
        }
        for (movie in unqFlix.movies) {
            if (movie.state.toString().contains("Available")) {
                contents.add(ContentMapper(movie.id, movie.description, movie.title, true))
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

}