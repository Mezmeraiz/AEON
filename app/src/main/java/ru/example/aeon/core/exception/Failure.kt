package ru.example.aeon.core.exception

sealed class Failure {

    object ServerError : Failure()
    object AuthError : Failure()

    data class Exception(val message: String?) : Failure()

}