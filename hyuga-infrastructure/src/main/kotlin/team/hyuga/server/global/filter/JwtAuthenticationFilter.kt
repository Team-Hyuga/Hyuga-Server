package team.hyuga.server.global.filter

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import team.hyuga.server.global.security.jwt.JwtConstant
import team.hyuga.server.global.security.jwt.JwtParser
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val jwtParser: JwtParser
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getToken(request)
        token?.let {
            SecurityContextHolder.getContext().authentication = jwtParser.getAuthentication(token)
        }

        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String? {
        val token = request.getHeader(JwtConstant.HEADER)

        return if (!token.isNullOrEmpty() && token.startsWith(JwtConstant.PREFIX)) {
            token.substring(JwtConstant.PREFIX.length);
        } else null;
    }
}