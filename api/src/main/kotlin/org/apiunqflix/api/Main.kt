package org.apiunqflix.api

import data.getUNQFlix
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import javalinjwt.JWTAccessManager
import org.apiunqflix.controller.UNQFlixController
import org.apiunqflix.controller.UserController

enum class Roles: Role {
    ANYONE, USER
}
fun main() {
    val app = Javalin.create {
        it.defaultContentType = "application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
    }
    app.start(7000)
    //resources
    val backend = getUNQFlix()
    val tokenJWT = TokenJWT()
    val jwtAccessManager = JWTAccessManager(tokenJWT,backend)

    //controllers
    val userController = UserController(backend, tokenJWT)
    val unqFlixController = UNQFlixController(backend, tokenJWT)
    //

    app.routes {
        path("/login") {
            post(userController::login, mutableSetOf<Role>(Roles.ANYONE))
        }
        path("/User") {
            get(userController::getUserFeatures, mutableSetOf<Role>(Roles.USER))
            path("/fav") {
                path("/:idContent") {
                    post(userController::addUserFavContent, mutableSetOf<Role>(Roles.USER))
                }
            }
            path("/lastSeen") {
                post(userController::addUserLastSeen, mutableSetOf<Role>(Roles.USER))
            }
        }
        path("/banners"){
            get(unqFlixController::getAllBanners, mutableSetOf<Role>(Roles.USER))
        }
        path("/content") {
            get(unqFlixController::getAvailableContent, mutableSetOf<Role>(Roles.USER))
        //    path("/:contentId") {
        //        get(unqFlixController::getContentById, mutableSetOf<Role>(Roles.USER))
        //    }
        }
        //path("/search?text={text}") {
        //    get(unqFlixController::searchText, mutableSetOf<Role>(Roles.USER))
        //}

    }
}