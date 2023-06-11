package team.hyuga.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HyugaApplication

fun main(args: Array<String>) {
    runApplication<HyugaApplication>(*args)
}
