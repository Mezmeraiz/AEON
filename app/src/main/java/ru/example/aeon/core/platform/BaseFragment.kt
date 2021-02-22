package ru.example.aeon.core.platform

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import ru.example.aeon.R
import ru.example.aeon.core.exception.Failure
import ru.example.aeon.core.extension.snackBar
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val progressDialog by lazy {
        AlertDialog.Builder(requireContext())
            .setView(R.layout.progress_dialog)
            .create()
    }

    inline fun <reified T : BaseViewModel> viewModel(block: T.() -> Unit = {}): T {
        val vm = ViewModelProvider(this, viewModelFactory)[T::class.java]
        vm.failureData.observe(this, Observer {
            handleFailure(it)
        })
        vm.progressData.observe(this, Observer {
            updateProgress(it)
        })
        vm.block()
        return vm
    }

    fun handleFailure(failure: Failure) {
        when (failure) {
            Failure.ServerError -> showMessage(getString(R.string.server_error))
            Failure.AuthError -> showMessage(getString(R.string.auth_error))
            is Failure.Exception  -> showMessage(failure.message)
        }
    }

    fun showMessage(message: String?){
        snackBar(message)
    }

    fun updateProgress(progress: Boolean){
        if (progress) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

}