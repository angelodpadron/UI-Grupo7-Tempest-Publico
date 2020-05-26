package org.apiunqflix.controller

import data.idGenerator
import domain.ExistsException
import domain.UNQFlix
import domain.User
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse
import io.javalin.http.NotFoundResponse
import org.apiunqflix.api.TokenJWT
import org.apiunqflix.mapper.ContentViewMapper
import org.apiunqflix.mapper.LoginUserMapper
import org.apiunqflix.mapper.UserRegisterMapper
import org.apiunqflix.mapper.UserViewMapper

class UserController (val unqflix: UNQFlix, val token: TokenJWT){

    fun login(ctx: Context){

        val entryToken = ctx.header("Authorization")

        if (entryToken == null) {
            val loginUser = ctx.bodyValidator<LoginUserMapper>()
                    .check({
                        it.email != null && it.password != null
                    }, "Datos incompletos").
                    get()

            val user = unqflix.users.find { it.email == loginUser.email && it.password == loginUser.password } ?: throw NotFoundResponse("Usuario o contraseña incorrectos")
            ctx.header("Authentication", token.generateToken(user))
            ctx.status(200)
            ctx.json(mapOf("result" to "ok"))

        }
        else{
            token.validate(entryToken)
            ctx.status(200)
            ctx.json(mapOf("result" to "ok"))
        }
    }

    fun getUser(ctx: Context){
        val id = ctx.header("Authorization")?.let { token.validate(it) }
        val user = unqflix.users.find { it.id == id } ?: throw NotFoundResponse("User not found")

        val viewUser = UserViewMapper(
                user.name,
                user.image,
                user.favorites.map { ContentViewMapper(it.id, it.description, it.title, it.state.toString().contains("Available"))} as MutableList<ContentViewMapper>,
                user.lastSeen.map { ContentViewMapper (it.id, it.description, it.title, it.state.toString().contains("Available"))} as MutableList<ContentViewMapper>
        )

        ctx.status(200)
        ctx.json(viewUser)
    }

    fun register(ctx: Context){
        val newUser = ctx.bodyValidator<UserRegisterMapper>()
            .check({
                it.name != null && it.email != null && it.password != null && it.creditCard != null && it.image != null
            }, "Datos incompletos. Verificar que cada campo este completo")
            .get()

        val newUserModel = registerIntoModel(newUser)


        ctx.status(201)
        ctx.header("Registration", token.generateToken(newUserModel))
        ctx.json(mapOf("message" to "ok"))
    }

    private fun registerIntoModel(userMapper: UserRegisterMapper): User{
        val newUserModel = User(idGenerator.nextUserId(), userMapper.name!!, userMapper.creditCard!!, userMapper.image!!, userMapper.email!!, userMapper.password!!, mutableListOf(), mutableListOf())
        try {
            unqflix.addUser(newUserModel)
        }
        catch (e: ExistsException){
            throw ForbiddenResponse("El email ya fue registrado por otro usuario")
        }
        return newUserModel
    }


}