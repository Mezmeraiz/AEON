package ru.example.aeon.core.repositories

import javax.inject.Inject
import ru.example.aeon.core.api.LoginApi
import ru.example.aeon.core.api.entities.Token
import ru.example.aeon.core.functional.Result
import ru.example.aeon.core.prefs.AppPreferencesHelper

class LoginRepository @Inject constructor(private val loginApi: LoginApi,
                                          private val preferencesHelper: AppPreferencesHelper){

    suspend fun login(login: String, password: String): Result<Token> =
        loginApi.login(login, password)

    fun getToken(): String? = preferencesHelper.token

    fun setToken(token: String?) {
        preferencesHelper.token = token
    }

}