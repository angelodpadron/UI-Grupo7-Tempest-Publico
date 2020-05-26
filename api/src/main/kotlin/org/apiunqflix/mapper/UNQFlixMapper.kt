package org.apiunqflix.mapper

import java.time.Duration

data class UserViewMapper (val name: String, val email: String, val creditCard: String, val image: String)
data class LoginUserMapper (val email: String, val password: String)
data class UserRegisterMapper (val name: String?, val email: String?, val password: String?, val creditCard: String?, val image: String?)
data class ContentMapper (val id: String, val description: String , val title: String ,val state: Boolean)
data class UserActivityFeaturesMapper(var name: String, var image: String, var favorites: MutableList<ContentMapper>, var lastSeen: MutableList<ContentMapper>)
data class AllContentsMapper(val content: MutableList<ContentMapper>)
data class AllBannersMapper(val banners: MutableList<ContentMapper>)
data class ChapterMapper(val id: String, val title: String, val description: String, val duration: Int, val video: String, val thumbnail: String)
data class SeasonMapper(val id: String,val title: String,val description: String,val poster: String,val chapters: MutableList<ChapterMapper>)
data class CategoryMapper(val name: String)
data class SerieMapper(var id: String, var title: String, var description: String, var poster: String, var categories: MutableList<CategoryMapper>, var relatedContent:MutableList<ContentMapper>, var seasons: MutableList<SeasonMapper>)
data class MovieMapper(var id:String, var title: String, var description: String, var poster: String, var video: String, var duration: Int, var actores: MutableList<String>, var directors: MutableList<String>, var categories: MutableList<CategoryMapper>, var relatedContent: MutableList<ContentMapper>)
data class FavContentMapper(var favContent: MutableList<ContentMapper>)
data class CreateContentUser (val id: String, val name: String)