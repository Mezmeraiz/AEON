package ru.example.aeon.features.login

import ru.example.aeon.core.functional.None
import ru.example.aeon.core.functional.SingleLiveEvent
import ru.example.aeon.core.platform.BaseViewModel
import ru.example.aeon.core.use_cases.LoginUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase):
    BaseViewModel() {

    val login = SingleLiveEvent<None>()

    fun login(login: String, password: String){
        updateProgress(true)
        loginUseCase(LoginUseCase.Params(login, password)){
            it.either(::handleFailure, ::handleLogin)
        }
    }

    private fun handleLogin(none: None){
        this.login.value = none
        updateProgress(false)
    }

    override fun onCleared() {
        super.onCleared()
        loginUseCase.unsubscribe()
    }

}