package ru.example.aeon.core.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.example.aeon.features.login.LoginFragment
import ru.example.aeon.features.payment.PaymentFragment

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun loginFragmentInjector(): LoginFragment

    @ContributesAndroidInjector
    fun paymentFragmentInjector(): PaymentFragment

}