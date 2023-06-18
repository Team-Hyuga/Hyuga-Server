package team.hyuga.server.domain.auth.spi

import team.hyuga.server.domain.auth.model.RefreshToken

interface QueryRefreshTokenPort {

    fun queryByToken(token: String): RefreshToken?
}