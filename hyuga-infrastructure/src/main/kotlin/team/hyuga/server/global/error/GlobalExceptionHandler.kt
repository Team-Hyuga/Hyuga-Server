package team.hyuga.server.global.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import team.hyuga.server.common.error.BusinessException
import team.hyuga.server.global.exception.BadRequestException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBindException(
        e: BindException
    ) = ErrorResponse.of(e.bindingResult)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException
    ) = ErrorResponse.of(e.bindingResult)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleMethodArgumentTypeMismatchException(
        e: MethodArgumentTypeMismatchException
    ) = ErrorResponse.of(e)

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(
        e: HttpRequestMethodNotSupportedException
    ) = ErrorResponse.of(e)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException::class)
    protected fun handleNullPointerException(
        e: NullPointerException
    ) = ErrorResponse.of(BadRequestException)

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(
        e: BusinessException
    ): ResponseEntity<ErrorResponse> {
        val errorProperty = e.errorProperty
        return ResponseEntity
            .status(errorProperty.status())
            .body(ErrorResponse.of(e))
    }
}