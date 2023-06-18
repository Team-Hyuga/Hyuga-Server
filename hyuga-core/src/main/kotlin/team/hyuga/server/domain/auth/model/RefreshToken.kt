package team.hyuga.server.domain.auth.model

import team.hyuga.server.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class RefreshToken(

    val token: String,

    val userId: UUID,

    val authType: AuthType,

    val ttl: Int
)
