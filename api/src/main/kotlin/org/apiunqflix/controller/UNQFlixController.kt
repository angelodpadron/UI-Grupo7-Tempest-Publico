package org.apiunqflix.controller

import data.idGenerator
import domain.UNQFlix
import domain.User
import io.javalin.http.Context
import org.apiunqflix.api.TokenJWT
import org.apiunqflix.mapper.LoginUserMapper
import org.apiunqflix.mapper.UserRegisterMapper

class NotFoundUser: Exception ("User not found")

class UserController (val unqflix: UNQFlix, val token: TokenJWT){

    fun login(ctx: Context){
        val loginUser = ctx.bodyAsClass(LoginUserMapper::class.java)
        val user = unqflix.users.find { it.email == loginUser.email && it.password == loginUser.password } ?: throw NotFoundUser()
        ctx.status(201)
        ctx.header("Authentication", token.generateToken(user))
        ctx.json(user)

    }

    fun register(ctx: Context){
        val newUser = ctx.bodyValidator<UserRegisterMapper>()
            .check({
                it.name != null && it.email != null && it.password != null && it.creditCard != null && it.image != null
            }, "Datos incompletos. Verificar que cada campo este completo")
            .get()

        //Temporal; pendiente excepciones
        val newUserModel = User(idGenerator.nextUserId(),
            newUser.name!!,
            newUser.creditCard!!,
            newUser.image!!,
            newUser.email!!,
            newUser.password!!,
            mutableListOf(),
            mutableListOf())

        unqflix.addUser(newUserModel)

        ctx.status(201)
        ctx.header("Authentication", token.generateToken(newUserModel))
        ctx.json(mapOf("message" to "ok"))

    }


}

