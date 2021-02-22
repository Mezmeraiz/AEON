package ru.example.aeon.core.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.example.aeon.core.exception.Failure
import ru.example.aeon.core.exception.ServerException
import ru.example.aeon.core.functional.Result
import java.text.SimpleDateFormat
import java.util.*

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeNotNull(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeNotNull(liveData: L, body: () -> Unit) =
    liveData.observe(this, Observer<T> { body() })

fun  parseError(e: Throwable): Failure {
    if (e is ServerException){
        return when (e.message) {
            "User authorization failed" -> Failure.AuthError
            else -> Failure.ServerError
        }
    }
    return Failure.Exception(e.localizedMessage)
}

fun  <T> parseResponse(result: Result<T>): T {
    if (result.success){
        return result.response!!
    } else {
        throw ServerException(result.error!!.errorMsg)
    }
}

fun Fragment.snackBar(message: String? = "unknown error", length: Int = Snackbar.LENGTH_SHORT) {
    activity?.let {
        Snackbar.make(it.window.decorView.rootView, message.toString(), length).show()
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

