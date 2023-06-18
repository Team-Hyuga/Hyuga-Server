package team.hyuga.server.domain.auth.persistence

import org.springframework.stereotype.Component
import team.hyuga.server.domain.auth.persistence.repository.RefreshTokenJpaRepository

@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenJpaRepository: RefreshTokenJpaRepository
) {
    
}