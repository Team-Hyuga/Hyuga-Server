package team.hyuga.server.global.error

import org.springframework.validation.BindingResult
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import team.hyuga.server.common.error.BusinessException
import team.hyuga.server.common.error.ErrorProperty
import java.util.ArrayList

data class ErrorResponse(
    val status: Int,
    val message: String,
    val fieldErrors: List<FieldError>
) {

    companion object {

        fun of(bindingResult: BindingResult) = of(
            errorProperty = GlobalErrorCode.BAD_REQUEST,
            fieldErrors = FieldError.of(bindingResult)
        )

        fun of(e: MethodArgumentTypeMismatchException): ErrorResponse {
            val value = e.value
            val fieldErrors = FieldError.of(
                field = e.parameter.parameterName ?: "",
                value = value.toString(),
                reason = "${e.requiredType!!.name} 잘못된 타입입니다."
            )

            return of(
                errorProperty = GlobalErrorCode.BAD_REQUEST,
                fieldErrors = fieldErrors
            )
        }

        fun of(e: HttpRequestMethodNotSupportedException) = of(
            errorProperty = GlobalErrorCode.METHOD_NOT_ALLOWED,
            fieldErrors = emptyList()
        )

        fun of(e: BusinessException) = ErrorResponse(
            status = e.errorProperty.status(),
            message = e.errorProperty.message(),
            fieldErrors = emptyList()
        )

        private fun of(errorProperty: ErrorProperty, fieldErrors: List<FieldError>) = ErrorResponse(
            status = errorProperty.status(),
            message = errorProperty.message(),
            fieldErrors = fieldErrors
        )
    }
}

data class FieldError(
    val field: String,
    val value: String,
    val reason: String
) {

    companion object {

        fun of(bindingResult: BindingResult): List<FieldError> {
            val filedErrors = bindingResult.fieldErrors
            return filedErrors.map {
                FieldError(
                    field = it.field,
                    value = it.rejectedValue.toString(),
                    reason = it.defaultMessage!!
                )
            }
        }

        fun of(field: String, value: String, reason: String): List<FieldError> {
            val fieldErrors: MutableList<FieldError> = ArrayList()
            fieldErrors.add(FieldError(field, value, reason))
            return fieldErrors
        }
    }
}
