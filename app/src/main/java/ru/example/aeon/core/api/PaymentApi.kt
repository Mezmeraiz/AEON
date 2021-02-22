package ru.example.aeon.core.api

import retrofit2.http.*
import ru.example.aeon.core.api.entities.Payment
import ru.example.aeon.core.functional.Result

interface PaymentApi {

    @GET("payments")
    suspend fun getPayments(@Query("token") token: String): Result<List<Payment>>

}