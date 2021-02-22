package ru.example.aeon.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.example.aeon.core.di.DaggerViewModelFactory
import ru.example.aeon.core.di.ViewModelKey
import ru.example.aeon.features.login.LoginViewModel
import ru.example.aeon.features.payment.PaymentViewModel

@Module
abstract class ViewModelModule() {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentViewModel::class)
    abstract fun bindsSmsViewModel(viewModel: PaymentViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}