package team.hyuga.server.global.security.jwt

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.hyuga.server.domain.auth.dto.TokenResponse
import team.hyuga.server.domain.auth.model.AuthType
import team.hyuga.server.domain.auth.model.RefreshToken
import team.hyuga.server.domain.auth.spi.CommandRefreshTokenPort
import team.hyuga.server.domain.auth.spi.JwtPort
import team.hyuga.server.domain.user.model.Authority
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Component
class GenerateJwtAdapter(
    private val commandRefreshTokenPort: CommandRefreshTokenPort,
    private val jwtProperties: JwtProperties
) : JwtPort {

    override fun generateToken(userId: UUID, authority: Authority): TokenResponse {
        val accessToken = generateAccessToken(userId, authority)
        val refreshToken = generateRefreshToken(userId, authority)

        return TokenResponse(
            accessToken = accessToken,
            accessTokenExpiredAt = LocalDateTime.now().plusSeconds(jwtProperties.accessExp.toLong()),
            refreshToken = refreshToken,
            refreshTokenExpiredAt = LocalDateTime.now().plusSeconds(jwtProperties.refreshExp.toLong())
        )
    }

    private fun generateAccessToken(userId: UUID, authority: Authority) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtConstant.ACCESS)
            .setSubject(userId.toString())
            .claim(JwtConstant.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(userId: UUID, authority: Authority): String {
        val token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtConstant.ACCESS)
            .setSubject(userId.toString())
            .claim(JwtConstant.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()

        val refreshToken = RefreshToken(
            token = token,
            userId = userId,
            authType = AuthType.SIGN_UP,
            ttl = jwtProperties.refreshExp
        )
        commandRefreshTokenPort.saveRefreshToken(refreshToken)

        return token
    }
}