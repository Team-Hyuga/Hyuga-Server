plugins {
    kotlin("jvm") version PluginVersions.JVM_VERSION
}

subprojects {

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        version = PluginVersions.JVM_VERSION
    }

    apply {
        apply {
            plugin("org.jetbrains.kotlin.kapt")
            version = PluginVersions.KAPT_VERSION
        }
    }

    dependencies {

        // kotlin
        implementation(Dependencies.KOTLIN_REFLECT)
        implementation(Dependencies.KOTLIN_JDK)
    }
}

allprojects {
    group = "team.hyuga.server"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        compileJava {
            sourceCompatibility = JavaVersion.VERSION_17.majorVersion
        }

        test {
            useJUnitPlatform()
        }
    }
}

tasks.getByName<Jar>("jar") {
    enabled = false
}