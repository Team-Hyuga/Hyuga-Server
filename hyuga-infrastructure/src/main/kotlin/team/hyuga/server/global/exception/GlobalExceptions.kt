package team.hyuga.server.global.exception

import team.hyuga.server.common.error.BusinessException
import team.hyuga.server.global.error.GlobalErrorCode

object BadRequestException : BusinessException(
    GlobalErrorCode.BAD_REQUEST
)

object MethodNotAllowedException : BusinessException(
    GlobalErrorCode.METHOD_NOT_ALLOWED
)

object InternalServerErrorException : BusinessException(
    GlobalErrorCode.INTERNAL_SERVER_ERROR
)
