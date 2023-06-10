package team.hyuga.server.global.error

import team.hyuga.server.common.error.ErrorProperty

enum class GlobalErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    BAD_REQUEST(400, "잘못된 요청입니다."),

    METHOD_NOT_ALLOWED(405, "지원하지 않는 Http 메서드입니다."),

    INTERNAL_SERVER_ERROR(500, "서버 에러가 발생했습니다.")
    ;

    override fun status() = status
    override fun message(): String = message
}