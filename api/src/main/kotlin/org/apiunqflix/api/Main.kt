package org.apiunqflix.api

import data.getUNQFlix
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import javalinjwt.JWTAccessManager
import org.apiunqflix.controller.UNQFlixController
import org.apiunqflix.controller.UserController

enum class Rol: Role {
    INVITED, ACTIVE_USER
}

fun main() {

    //resources
    val backend = getUNQFlix()
    val tokenJWT = TokenJWT()
    val accessManager = JWTManager(tokenJWT, backend)

    //controllers
    val userController = UserController(backend, tokenJWT)
    val unqFlixController = UNQFlixController(backend, tokenJWT)


    val app = Javalin.create {
        it.defaultContentType ="application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
        it.accessManager(accessManager)
    }

    app.start(7000)

    app.routes {
        path("/login") {
            post(userController::login, mutableSetOf<Role>(Rol.INVITED, Rol.ACTIVE_USER))
        }
        path("/register"){
            post(userController::register, mutableSetOf<Role>(Rol.INVITED))
        }
        path("/user") {
            get(userController::getUserFeatures, mutableSetOf<Role>(Rol.ACTIVE_USER))
            path("/fav") {
                    post(userController::addUserFavContent, mutableSetOf<Role>(Rol.ACTIVE_USER))
            }
            path("/lastSeen") {
                post(userController::addUserLastSeen, mutableSetOf<Role>(Rol.ACTIVE_USER))
            }
        }
        path("/banners"){
            get(unqFlixController::getAllBanners, mutableSetOf<Role>(Rol.INVITED,Rol.ACTIVE_USER))
        }
        path("/content") {
            get(unqFlixController::getAvailableContent, mutableSetOf<Role>(Rol.INVITED,Rol.ACTIVE_USER))
            path("/:contentId") {
                get(unqFlixController::getContentById, mutableSetOf<Role>(Rol.INVITED,Rol.ACTIVE_USER))
            }
        }
        path("/search") {
            get(unqFlixController::searchText, mutableSetOf<Role>(Rol.INVITED))
        }
    }
}