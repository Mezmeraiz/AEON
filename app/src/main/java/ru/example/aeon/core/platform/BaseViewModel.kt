package ru.example.aeon.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.example.aeon.core.exception.Failure
import ru.example.aeon.core.functional.SingleLiveEvent

abstract class BaseViewModel: ViewModel(){

    val failureData = SingleLiveEvent<Failure>()

    val progressData: MutableLiveData<Boolean> = MutableLiveData()

    fun handleFailure(failure: Failure) {
        failureData.value = failure
        updateProgress(false)
    }

    fun updateProgress(progress: Boolean) {
        progressData.value = progress
    }

}