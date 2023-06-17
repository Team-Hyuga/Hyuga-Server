package team.hyuga.server.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import team.hyuga.server.common.error.BusinessException
import team.hyuga.server.global.error.ErrorResponse
import team.hyuga.server.global.exception.InternalServerErrorException
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionHandlerFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (exception: Exception) {
            when(exception) {
                is BusinessException -> setErrorResponse(response, exception)
                else -> {
                    exception.printStackTrace()
                    setErrorResponse(response, InternalServerErrorException)
                }
            }
        }
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        exception: BusinessException
    ) {
        response.apply {
            characterEncoding = StandardCharsets.UTF_8.name()
            status = exception.errorProperty.status()
            contentType = MediaType.APPLICATION_JSON_VALUE
            writer.write(
                objectMapper.writeValueAsString(ErrorResponse.of(exception))
            )
        }
    }
}
