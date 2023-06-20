package team.hyuga.server.domain.auth.mapper

import org.mapstruct.Mapper
import team.hyuga.server.domain.GenericMapper
import team.hyuga.server.domain.auth.model.RefreshToken
import team.hyuga.server.domain.auth.persistence.entity.RefreshTokenEntity

@Mapper(componentModel = "spring")
abstract class RefreshTokenMapper : GenericMapper<RefreshToken, RefreshTokenEntity>