package org.apiunqflix.mapper

data class UserViewMapper(val name: String, val image: String, val favorites: MutableList<ContentViewMapper>, val lastSeen: MutableList<ContentViewMapper>)
data class ContentViewMapper(val id: String, val description: String, val title: String, val state: Boolean)

data class LoginUserMapper (val email: String? = null, val password: String? = null)

data class UserRegisterMapper (val name: String?, val email: String?, val password: String?, val creditCard: String?, val image: String?)
