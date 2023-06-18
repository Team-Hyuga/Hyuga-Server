package team.hyuga.server.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@EnableRedisRepositories
@Configuration
class RedisConfig(
    @Value("\${spring.redis.host}")
    val host: String,
    @Value("\${spring.redis.port}")
    val port: Int
) {

    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(host, port)
}