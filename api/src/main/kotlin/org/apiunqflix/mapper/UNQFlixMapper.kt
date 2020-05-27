package org.apiunqflix.mapper

//USER mappers
data class UserViewMapper(val name: String, val image: String, val favorites: MutableList<ContentViewMapper>, val lastSeen: MutableList<ContentViewMapper>)
data class ContentViewMapper(val id: String, val description: String, val title: String, val state: Boolean)
data class LoginUserMapper (val email: String? = null, val password: String? = null)
data class UserRegisterMapper (val name: String?, val email: String?, val password: String?, val creditCard: String?, val image: String?)

//CONTENTS mappers
data class ChapterMapper(val id: String, val title: String, val description: String, val duration: Int, val video: String, val thumbnail: String)
data class SeasonMapper(val id: String,val title: String,val description: String,val poster: String,val chapters: MutableList<ChapterMapper>)
data class CategoryMapper(val name: String)
data class SerieMapper(var id: String, var title: String, var description: String, var poster: String, var categories: MutableList<CategoryMapper>, var relatedContent:MutableList<ContentViewMapper>, var seasons: MutableList<SeasonMapper>)
data class MovieMapper(var id:String, var title: String, var description: String, var poster: String, var video: String, var duration: Int, var actores: MutableList<String>, var directors: MutableList<String>, var categories: MutableList<CategoryMapper>, var relatedContent: MutableList<ContentViewMapper>)
data class CreateContentUser(val id: String? = null)

data class AvailableContentsMapper(val content: MutableList<ContentViewMapper>)

data class BannerMapper (val id: String, val title: String, val poster: String)
data class AllBannersMapper(val banners: MutableList<BannerMapper>)
