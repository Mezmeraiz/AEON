package ru.example.aeon.core.api

import retrofit2.http.*
import ru.example.aeon.core.api.entities.Token
import ru.example.aeon.core.functional.Result

interface LoginApi {

    @FormUrlEncoded
    @POST("login ")
    suspend fun login(@Field("login") login : String,
                      @Field("password") password : String): Result<Token>

}