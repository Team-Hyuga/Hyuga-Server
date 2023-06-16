package team.hyuga.server.global.security.exception

import team.hyuga.server.common.error.ErrorProperty

enum class SecurityErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    EXPIRED_TOKEN(401, "만료된 JWT 토큰입니다."),
    INVALID_TOKEN(401, "잘못된 JWT 토큰입니다.")
    ;


    override fun status() = status
    override fun message() = message
}