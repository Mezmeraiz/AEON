package ru.example.aeon.features.payment

import androidx.lifecycle.MutableLiveData
import ru.example.aeon.core.api.entities.Payment
import ru.example.aeon.core.functional.None
import ru.example.aeon.core.functional.SingleLiveEvent
import ru.example.aeon.core.platform.BaseViewModel
import ru.example.aeon.core.use_cases.GetTokenUseCase
import ru.example.aeon.core.use_cases.LogoutUseCase
import ru.example.aeon.core.use_cases.PaymentUseCase
import javax.inject.Inject

class PaymentViewModel @Inject constructor(private val paymentUseCase: PaymentUseCase,
                                           private val getTokenUseCase: GetTokenUseCase,
                                           private val logoutUseCase: LogoutUseCase):
    BaseViewModel() {

    val payments = MutableLiveData<List<Payment>>()

    val login = SingleLiveEvent<None>()

    @Inject
    fun getToken() {
        getTokenUseCase(None){
            it.either(::handleFailure, ::handleToken)
        }
    }

    private fun handleToken(token: String?){
        if (token != null) {
            getPayments()
        } else {
            login.value = None
        }
    }

    fun getPayments(){
        updateProgress(true)
        paymentUseCase(None){
            it.either(::handleFailure, ::handlePayments)
        }
    }

    fun logout(){
        logoutUseCase(None){
            it.either(::handleFailure, ::handleLogout)
        }
    }

    private fun handlePayments(payments: List<Payment>){
        this.payments.value = payments
        updateProgress(false)
    }

    private fun handleLogout(none: None){
        login.value = none
    }

    override fun onCleared() {
        super.onCleared()
        paymentUseCase.unsubscribe()
        getTokenUseCase.unsubscribe()
        logoutUseCase.unsubscribe()
    }

}