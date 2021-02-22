package ru.example.aeon.core.use_cases

import ru.example.aeon.core.exception.Failure
import ru.example.aeon.core.extension.parseResponse
import ru.example.aeon.core.functional.Either
import ru.example.aeon.core.functional.None
import ru.example.aeon.core.repositories.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository)
    : UseCase<None, LoginUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        loginRepository.setToken(parseResponse(loginRepository.login(params.login,
                params.password)).token)
        return Either.Right(None)
    }

    data class Params(val login: String, val password: String)

}