package com.example.myapplication.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.workOrder.WorkOrdersActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    lateinit var binding: FragmentLoginBinding
    val viewModel: LoginViewModel by viewModels()

    companion object {
        const val tag = "LoginFragment"
        fun newInstance() = LoginFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this
        init()
        return binding.root
    }

    private fun init (){
        if(viewModel.isLoggedIn()){
            val intent = Intent(context, WorkOrdersActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        binding.bLogin.setOnClickListener {
            if(!viewModel.isLoggedIn()) {
                viewModel.login()
                val intent = Intent(context, WorkOrdersActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

}