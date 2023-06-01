object Dependencies {

    // kotlin
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_JDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"

    // web
    const val SPRING_WEB = "org.springframework.boot:spring-boot-starter-web"

    // validation
    const val VALIDATION = "org.springframework.boot:spring-boot-starter-web"

    // security
    const val SPRING_SECURITY = "org.springframework.boot:spring-boot-starter-security"

    // database
    const val SPRING_DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val MYSQL = "com.mysql:mysql-connector-j"
    const val REDIS = "org.springframework.boot:spring-boot-starter-data-redis"

    // querydsl
    const val QUERYDSL = "com.querydsl:querydsl-jpa:${DependencyVersions.QUERYDSL}"
    const val QUERYDSL_PROCESSOR = "com.querydsl:querydsl-apt:${DependencyVersions.QUERYDSL}:jpa"

    // jwt
    const val JWT = "io.jsonwebtoken:jjwt:${DependencyVersions.JWT_VERSION}"

    // uuid
    const val UUID = "com.fasterxml.uuid:java-uuid-generator:${DependencyVersions.UUID_VERSION}"

    // aws
    const val AWS = "org.springframework.cloud:spring-cloud-starter-aws:${DependencyVersions.AWS_VERSION}"
}