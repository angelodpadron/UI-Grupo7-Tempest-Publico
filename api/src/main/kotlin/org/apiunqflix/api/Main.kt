package org.apiunqflix.api

import data.getUNQFlix
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import org.apiunqflix.controller.UserController

enum class Rol: Role {
    ANYONE, USER, ADMIN
}

fun main() {
    val app = Javalin.create {
        it.defaultContentType ="application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
    }
    app.start(7000)

    val backend = getUNQFlix()
    val tokenJWT = TokenJWT()

    //controllers
    val userController = UserController(backend, tokenJWT)

    app.routes{
        // usuarios
        path("/login"){
            post(userController::login)
        }
        path("/register"){
            post(userController::register)
        }
        path("/user"){
            path("/:id"){
                get(userController::getUser)
            }
        }
    }

}