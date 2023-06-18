package team.hyuga.server.domain.auth.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.hyuga.server.domain.auth.mapper.RefreshTokenMapper
import team.hyuga.server.domain.auth.model.RefreshToken
import team.hyuga.server.domain.auth.persistence.repository.RefreshTokenJpaRepository
import team.hyuga.server.domain.auth.spi.RefreshTokenPort

@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenJpaRepository: RefreshTokenJpaRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : RefreshTokenPort {

    override fun saveRefreshToken(refreshToken: RefreshToken) =
        refreshTokenMapper.toDomain(
            refreshTokenJpaRepository.save(
                refreshTokenMapper.toEntity(refreshToken)
            )
        )!!

    override fun queryByToken(token: String) = refreshTokenMapper.toDomain(
        refreshTokenJpaRepository.findByIdOrNull(token)
    )
}
