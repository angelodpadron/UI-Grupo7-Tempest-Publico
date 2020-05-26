package org.apiunqflix.api

import domain.UNQFlix
import domain.User
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse

class NotFoundToken : Exception ("message")
class NotFoundUser : Exception ("message")

class JWTAccessManager (val tokenJWT: TokenJWT, val unqFlix: UNQFlix): AccessManager {

    fun getUser(token :String): User? {
        try{
            val userId= tokenJWT.validate(token)
            return  unqFlix.users.find{it.id == userId}
        }
        catch(e: NotFoundToken){
            throw UnauthorizedResponse ("Token not found")
        }
        catch(e: NotFoundUser){
            throw UnauthorizedResponse ("Invalid Token")
        }

    }

    override fun manage( handler: Handler , ctx : Context , roles : MutableSet<Role>){
        val token = ctx.header("userAuthorization") // devuelve un string o null , el null porque nadie me pasa un header o un string que seria el token que yo busco
        when{
            token == null && roles.contains(Roles.ANYONE)->  handler.handle(ctx)//En este caso el ubnico que lo tiene )
            token == null -> throw  UnauthorizedResponse ("Token not found")
            roles.contains (Roles.ANYONE)-> handler.handle(ctx)
            roles.contains (Roles.USER)-> {
                getUser (token)
                handler.handle(ctx)
            } // busco mediante el token el idDelUsuario y verifico que este en mi sistema  y no sea malicioso , entonces se hace un validator de acceso
        }
    }
}