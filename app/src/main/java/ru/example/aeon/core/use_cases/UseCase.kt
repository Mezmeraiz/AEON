package ru.example.aeon.core.use_cases

import kotlinx.coroutines.*
import ru.example.aeon.core.exception.Failure
import ru.example.aeon.core.extension.parseError
import ru.example.aeon.core.functional.Either

abstract class UseCase<out Type, in Params> {

    var scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val handler  = CoroutineExceptionHandler { _, throwable ->
            onResult(Either.Left(parseError(throwable)))
        }
        scope.launch(handler) {
            onResult(run(params))
        }
    }

    fun unsubscribe() {
        scope.cancel()
    }

}
