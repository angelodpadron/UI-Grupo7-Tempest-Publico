package org.apiunqflix.api

import data.getUNQFlix
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import javalinjwt.JWTAccessManager
import org.apiunqflix.controller.UNQFlixController
import org.apiunqflix.controller.UserController

enum class Roles : Role{
    ANYONE,USER,ADMIN
}
fun main() {
    val app = Javalin.create {
        it.defaultContentType ="application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
    }
    app.start(7000)
    val jwtAccessManager= JWTAccessManager()
    val unqflix = getUNQFlix()
    val tokenJWT = TokenJWT()

    //controllers
    val userController = UserController(unqflix, tokenJWT)
    val unqFlixController = UNQFlixController(unqflix,tokenJWT)


    app.routes{
        // usuarios
        path("/register"){
            post(userController::register, mutableSetOf<Role>(Roles.ANYONE))
        }
        path("/login"){
            post(userController::login, mutableSetOf<Role>(Roles.ANYONE))
        }
        path("/User"){
            get (userController::getUserFeatures,mutableSetOf<Role>(Roles.USER,Roles.ADMIN))
            path("/fav"){
                path("/:idContent"){
                    post(userController::addUserFavConent,mutableSetOf<Role>(Roles.USER))
                }
            }
            path("/lastSeen"){
                post(userController::addUserLastSeen,mutableSetOf<Role>(Roles.USER,Roles.ADMIN))
            }
        }
        path("/content"){
            get(unqFlixController::getAvailableContent,mutableSetOf<Role>(Roles.USER,Roles.ADMIN))
            path("/:contentId"){
                get(unqFlixController::getContent,mutableSetOf<Role>(Roles.USER,Roles.ADMIN))
            }
        }
        path("/search?text={text}"){
            get(unqFlixController::searchText,mutableSetOf<Role>(Roles.USER,Roles.ADMIN))
        }

}