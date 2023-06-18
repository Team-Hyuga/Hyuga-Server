package team.hyuga.server.domain.auth.model

import team.hyuga.server.common.annotation.Aggregate

@Aggregate
data class RefreshToken(

    val token: String,

    val email: String,

    val authType: AuthType,

    val ttl: Int
)
