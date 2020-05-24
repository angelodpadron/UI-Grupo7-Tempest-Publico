package org.apiunqflix.mapper

data class UserViewMapper (val name: String, val email: String, val creditCard: String, val image: String)
data class LoginUserMapper (val email: String, val password: String)
data class UserRegisterMapper (val name: String?, val email: String?, val password: String?, val creditCard: String?, val image: String?)
