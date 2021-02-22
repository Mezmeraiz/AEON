package ru.example.aeon.features.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_payment.*
import ru.example.aeon.R
import ru.example.aeon.core.api.entities.Payment
import ru.example.aeon.core.extension.observeNotNull
import ru.example.aeon.core.functional.MarginDecoration
import ru.example.aeon.core.platform.BaseFragment
import ru.example.aeon.core.prefs.AppPreferencesHelper
import ru.example.aeon.features.login.LoginFragment
import javax.inject.Inject
import javax.inject.Named

class PaymentFragment: BaseFragment() {

    private val layoutId = R.layout.fragment_payment

    private lateinit var viewModel: PaymentViewModel

    @Inject
    lateinit var paymentAdapter: PaymentAdapter

    @Inject
    lateinit var appPreferencesHelper: AppPreferencesHelper

    @Inject
    @field:[Named("payment")]
    lateinit var marginDecoration: MarginDecoration

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = viewModel{
            observeNotNull(payments, ::handlePayments)
            observeNotNull(login, ::handleLogin)
        }
        return inflater.inflate(layoutId, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle
        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry, Observer { success ->
                if (success) {
                    viewModel.getPayments()
                } else {
                    requireActivity().finish()
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        ivLogout.setOnClickListener { viewModel.logout() }
    }

    private fun initRecyclerView() {
        recyclerView.addItemDecoration(marginDecoration)
        recyclerView.adapter = paymentAdapter
    }

    private fun handlePayments(payments: List<Payment>) {
        paymentAdapter.setData(payments)
    }

    private fun handleLogin() {
        findNavController().navigate(R.id.loginFragment)
    }

}