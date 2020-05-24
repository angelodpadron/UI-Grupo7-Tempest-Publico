package org.apiunqflix.api

import io.javalin.Javalin
import io.javalin.core.util.RouteOverviewPlugin

fun main() {
    val app = Javalin.create {
        it.defaultContentType ="aplication/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
    }
    app.start(7000)

}