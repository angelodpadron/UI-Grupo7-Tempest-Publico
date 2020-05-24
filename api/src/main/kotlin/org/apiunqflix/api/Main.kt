package org.apiunqflix.api

import data.getUNQFlix
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import org.apiunqflix.controller.UserController

fun main() {
    val app = Javalin.create {
        it.defaultContentType ="aplication/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
    }
    app.start(7000)

    val unqflix = getUNQFlix()
    val tokenJWT = TokenJWT()

    //controllers
    val userController = UserController(unqflix, tokenJWT)

    app.routes{
        // usuarios
        path("/register"){
            post(userController::register)
        }
        path("/login"){
            post(userController::login)
        }
    }

}