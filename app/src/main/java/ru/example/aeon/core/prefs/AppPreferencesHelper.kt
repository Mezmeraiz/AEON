package ru.example.aeon.core.prefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import ru.example.aeon.core.PREF_NAME
import ru.example.aeon.core.PREF_TOKEN
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(context: Application)  {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.getString(PREF_TOKEN, null)
        set(value) { prefs.edit().putString(PREF_TOKEN, value).apply() }

}