package ru.example.aeon.core.use_cases

import ru.example.aeon.core.exception.Failure
import ru.example.aeon.core.functional.Either
import ru.example.aeon.core.functional.None
import ru.example.aeon.core.repositories.LoginRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val loginRepository: LoginRepository)
    : UseCase<String?, None>() {

    override suspend fun run(params: None): Either<Failure, String?> {
        return Either.Right(loginRepository.getToken())
    }

}