package ru.example.aeon.core.functional

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L) : Either<L, Nothing>()

    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun either(functionLeft: (L) -> Any, functionRight: (R) -> Any): Any =
        when (this) {
            is Left -> functionLeft(a)
            is Right -> functionRight(b)
        }
}

