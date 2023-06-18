package team.hyuga.server.domain.auth.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.hyuga.server.domain.auth.persistence.entity.RefreshTokenEntity

interface RefreshTokenJpaRepository : CrudRepository<RefreshTokenEntity, String> {
}