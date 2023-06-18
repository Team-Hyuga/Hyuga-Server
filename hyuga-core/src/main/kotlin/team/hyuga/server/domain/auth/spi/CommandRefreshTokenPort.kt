package team.hyuga.server.domain.auth.spi

import team.hyuga.server.domain.auth.model.RefreshToken

interface CommandRefreshTokenPort {

    fun saveRefreshToken(refreshToken: RefreshToken): RefreshToken
}