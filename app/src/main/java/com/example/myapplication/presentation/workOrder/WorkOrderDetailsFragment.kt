package com.example.myapplication.presentation.workOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWorkOrderDetailsBinding
import com.example.myapplication.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkOrderDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentWorkOrderDetailsBinding

    val viewModel: WorkOrderDetailsViewModel by viewModels()

    companion object {
        const val tag = "WorkOrderDetailsFragment"
        private const val ARGS_TITLE = "args_title"
        private const val ARGS_DESCRIPTION = "args_description"
        fun newInstance(title: String, description: String) = WorkOrderDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(ARGS_TITLE, title)
                putString(ARGS_DESCRIPTION, description)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_work_order_details, container, false)
        binding.lifecycleOwner = this
        initObservers()
        val title : String = requireArguments().getString(ARGS_TITLE).toString()
        val description : String = requireArguments().getString(ARGS_DESCRIPTION).toString()
        viewModel.onViewCreated(title, description)
        return binding.root
    }



    private fun initObservers() {
        viewModel.titleLD.observe(viewLifecycleOwner, Observer {
            binding.tvTitle.text= it
        })
        viewModel.descriptionLD.observe(viewLifecycleOwner, Observer {
            binding.tvDescription.text= it
        })
    }

}