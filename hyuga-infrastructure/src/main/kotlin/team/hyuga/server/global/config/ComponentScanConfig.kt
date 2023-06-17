package team.hyuga.server.global.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import team.hyuga.server.common.annotation.ReadOnlyUseCase
import team.hyuga.server.common.annotation.UseCase

@Configuration
@ComponentScan(
    basePackages = ["team.hyuga.server"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                UseCase::class,
                ReadOnlyUseCase::class
            ]
        )
    ]
)
class ComponentScanConfig