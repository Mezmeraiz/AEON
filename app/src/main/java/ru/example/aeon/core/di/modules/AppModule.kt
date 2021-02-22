package ru.example.aeon.core.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.example.aeon.R
import ru.example.aeon.core.api.LoginApi
import ru.example.aeon.core.api.PaymentApi
import ru.example.aeon.core.functional.MarginDecoration
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun providePaymentApi(retrofit: Retrofit): PaymentApi {
        return retrofit.create(PaymentApi::class.java)
    }

    @Singleton
    @Named("payment")
    @Provides
    fun provideMarginDecoration(application: Application): MarginDecoration {
        return MarginDecoration(
            application.resources.getDimension(R.dimen.card_margin).toInt(),
            application.resources.getDimension(R.dimen.card_margin).toInt())
    }

}