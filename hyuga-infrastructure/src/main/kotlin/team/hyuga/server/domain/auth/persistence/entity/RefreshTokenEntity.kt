package team.hyuga.server.domain.auth.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import team.hyuga.server.domain.auth.model.AuthType
import java.util.UUID

@RedisHash
class RefreshTokenEntity(
    @Id
    val token: String,

    @Indexed
    val userId: UUID,

    val authType: AuthType,

    @TimeToLive
    val ttl: Int
)