package team.hyuga.server.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header.JWT_TYPE
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import team.hyuga.server.global.exception.InternalServerErrorException
import team.hyuga.server.global.security.auth.AuthDetailsService
import team.hyuga.server.global.security.exception.ExpiredTokenException
import team.hyuga.server.global.security.exception.InvalidTokenException

@Component
class JwtParser(
    private val authDetailsService: AuthDetailsService,
    private val jwtProperties: JwtProperties
) {

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token).apply {
            if (this.header[JWT_TYPE] != JwtConstant.ACCESS) {
                throw InvalidTokenException
            }
        }

        val userDetails = authDetailsService.loadUserByUsername(claims.body.subject)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token)
        } catch (exception: Exception) {
            when(exception) {
                is ExpiredJwtException -> throw ExpiredTokenException
                is JwtException -> throw InvalidTokenException
                else -> throw InternalServerErrorException
            }
        }
    }
}
