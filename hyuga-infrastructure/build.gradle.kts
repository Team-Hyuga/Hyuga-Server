plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGEMENT_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_VERSION
    kotlin("plugin.jpa") version PluginVersions.JPA_VERSION
}

dependencies {

    // web
    implementation(Dependencies.SPRING_WEB)

    // validation
    implementation(Dependencies.VALIDATION)

    // database
    implementation(Dependencies.SPRING_DATA_JPA)
    implementation(Dependencies.MYSQL)
    implementation(Dependencies.REDIS)

    // querydsl
    implementation(Dependencies.QUERYDSL)
    kapt(Dependencies.QUERYDSL_PROCESSOR)

    // security
    implementation(Dependencies.SPRING_SECURITY)

    // jwt
    implementation(Dependencies.JWT)

    // jackson
    implementation(Dependencies.JACKSON)

    // uuid
    implementation(Dependencies.UUID)

    // mapstruct
    implementation(Dependencies.MAPSTRUCT)
    kapt(Dependencies.MAPSTRUCT_PROCESSOR)

    // aws
    implementation(Dependencies.AWS)

    // impl
    implementation(project(":hyuga-core"))
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
