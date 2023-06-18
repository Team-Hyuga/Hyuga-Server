package team.hyuga.server.domain.auth.spi

import team.hyuga.server.domain.auth.dto.TokenResponse
import team.hyuga.server.domain.user.model.Authority
import java.util.UUID

interface JwtPort {

    fun generateToken(userId: UUID, authority: Authority): TokenResponse
}