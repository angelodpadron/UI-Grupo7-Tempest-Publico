package org.apiunqflix.api

import javalinjwt.JWTProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import domain.User
import javalinjwt.JWTGenerator
import java.lang.Exception

class UserGenerator : JWTGenerator<User> {
    override fun generate(user: User, algorithm: Algorithm): String {
        val token = JWT.create().withClaim("id", user.id)
        return token.sign(algorithm)
    }
}

class TokenJWT {

    val algorithm = Algorithm.HMAC256("very_secret")
    val generator = UserGenerator()
    val verifier = JWT.require(algorithm).build()
    val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(user: User): String{
        return provider.generateToken(user)
    }

    fun validate(token: String): String {
        val token = provider.validateToken(token)
        if (!token.isPresent) throw Exception() //token not found
        return token.get().getClaim("id").asString()
    }
}