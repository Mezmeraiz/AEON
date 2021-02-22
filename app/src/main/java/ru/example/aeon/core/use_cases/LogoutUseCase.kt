package ru.example.aeon.core.use_cases

import ru.example.aeon.core.exception.Failure
import ru.example.aeon.core.functional.Either
import ru.example.aeon.core.functional.None
import ru.example.aeon.core.repositories.LoginRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val loginRepository: LoginRepository)
    : UseCase<None, None>() {

    override suspend fun run(params: None): Either<Failure, None> {
        loginRepository.setToken(null)
        return Either.Right(None)
    }

}