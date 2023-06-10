package team.hyuga.server.common.error

interface ErrorProperty {

    fun status(): Int
    
    fun message(): String
}