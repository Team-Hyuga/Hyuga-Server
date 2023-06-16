package team.hyuga.server.global.security.exception

import team.hyuga.server.common.error.BusinessException

object ExpiredTokenException : BusinessException(
    SecurityErrorCode.EXPIRED_TOKEN
)

object InvalidTokenException : BusinessException(
    SecurityErrorCode.INVALID_TOKEN
)