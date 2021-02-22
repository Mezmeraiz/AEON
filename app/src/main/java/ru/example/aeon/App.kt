package ru.example.aeon

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ru.example.aeon.core.di.DaggerAppComponent

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}