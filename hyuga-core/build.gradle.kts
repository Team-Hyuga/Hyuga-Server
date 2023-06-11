plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {

    // spring transaction
    implementation(Dependencies.SPRING_TRANSACTION)
}

allOpen {
    annotation("team.hyuga.server.common.annotation.UseCase")
    annotation("team.hyuga.server.common.annotation.ReadOnlyUseCase")
}