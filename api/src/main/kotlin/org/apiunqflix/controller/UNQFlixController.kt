package org.apiunqflix.controller

import data.getUNQFlix
import domain.Available
import domain.Category
import domain.UNQFlix
import io.javalin.http.Context
import org.apiunqflix.api.TokenJWT
import org.apiunqflix.mapper.*

class UNQFlixController(val unqFlix: UNQFlix,val token: TokenJWT) {


}