package team.hyuga.server.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import team.hyuga.server.global.security.jwt.JwtParser

class FilterConfig(
    private val objectMapper: ObjectMapper,
    private val jwtParser: JwtParser
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtAuthenticationFilter(jwtParser), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionHandlerFilter(objectMapper), JwtAuthenticationFilter::class.java)
    }
}
