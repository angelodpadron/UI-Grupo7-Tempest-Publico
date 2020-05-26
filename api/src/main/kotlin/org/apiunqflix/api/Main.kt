package org.apiunqflix.api

import data.getUNQFlix
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import org.apiunqflix.controller.UserController

enum class Rol: Role {
    INVITED, ACTIVE_USER
}

fun main() {

    val backend = getUNQFlix()
    val tokenJWT = TokenJWT()
    val accessManager = JWTManager(tokenJWT, backend)

    //controllers
    val userController = UserController(backend, tokenJWT)

    val app = Javalin.create {
        it.defaultContentType ="application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
        it.accessManager(accessManager)
    }

    app.start(7000)

    app.routes{
        // usuarios
        path("/login"){
            post(userController::login, mutableSetOf<Role>(Rol.INVITED, Rol.ACTIVE_USER))
        }
        path("/register"){
            post(userController::register, mutableSetOf<Role>(Rol.INVITED))
        }
        path("/user"){
            get(userController::getUser, mutableSetOf<Role>(Rol.ACTIVE_USER))
        }
    }
}