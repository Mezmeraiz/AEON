package ru.example.aeon.core.use_cases

import ru.example.aeon.core.api.entities.Payment
import ru.example.aeon.core.exception.Failure
import ru.example.aeon.core.extension.parseResponse
import ru.example.aeon.core.functional.Either
import ru.example.aeon.core.functional.None
import ru.example.aeon.core.repositories.LoginRepository
import ru.example.aeon.core.repositories.PaymentRepository
import javax.inject.Inject

class PaymentUseCase @Inject constructor(private val paymentRepository: PaymentRepository,
                                         private val loginRepository: LoginRepository)
    : UseCase<List<Payment>, None>() {

    override suspend fun run(params: None): Either<Failure, List<Payment>> {
        return Either.Right(parseResponse(paymentRepository
                .getPayments(loginRepository.getToken()!!)))
    }

}