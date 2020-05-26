package org.apiunqflix.api

import domain.UNQFlix
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse

class JWTManager(val tokenJWT: TokenJWT, val backend: UNQFlix):AccessManager{

    fun verifyUserId(hash: String) {
        try {
            val id = tokenJWT.validate(hash)
            backend.users.find { it.id == id }?: throw UnauthorizedResponse("Token Invalido")   //muy optimo? /s
        }catch (e: NotFoundToken){
            throw UnauthorizedResponse("Token inexistente")
        }
    }

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
        val tokenJWT =ctx.header("Authorization")
        when{

            // LOGIN; GET USER
            tokenJWT == null && roles.contains(Rol.INVITED) -> handler.handle(ctx)
            tokenJWT == null -> throw UnauthorizedResponse("Token inexistente")

            roles.contains(Rol.ACTIVE_USER) -> {
                verifyUserId(tokenJWT)
                handler.handle(ctx)
            }

            // REGISTER
            roles.contains(Rol.INVITED) -> handler.handle(ctx)

        }
    }

}