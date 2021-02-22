package ru.example.aeon.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.SavedStateHandle
import kotlinx.android.synthetic.main.fragment_login.*
import ru.example.aeon.R
import ru.example.aeon.core.platform.BaseFragment
import androidx.navigation.fragment.findNavController
import ru.example.aeon.core.extension.observeNotNull

class LoginFragment: BaseFragment() {

    private val layoutId = R.layout.fragment_login

    private lateinit var viewModel: LoginViewModel

    private lateinit var savedStateHandle: SavedStateHandle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = viewModel{
            observeNotNull(login, ::handleLogin)
        }
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)
        btn_enter.setOnClickListener {
            viewModel.login(et_login.text.toString(), et_password.text.toString())
        }
    }

    private fun handleLogin() {
        savedStateHandle.set(LOGIN_SUCCESSFUL, true)
        findNavController().popBackStack()
    }

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

}