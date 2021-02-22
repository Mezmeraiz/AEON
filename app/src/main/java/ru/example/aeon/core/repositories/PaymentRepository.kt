package ru.example.aeon.core.repositories

import javax.inject.Inject
import ru.example.aeon.core.api.PaymentApi
import ru.example.aeon.core.api.entities.Payment
import ru.example.aeon.core.functional.Result

class PaymentRepository @Inject constructor(private val paymentApi: PaymentApi){

    suspend fun getPayments(token: String): Result<List<Payment>> =
        paymentApi.getPayments(token)

}