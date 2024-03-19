package com.example.myapplication.presentation.workOrder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.WorkOrderItemContent
import com.example.myapplication.databinding.FragmentWorkOrderListBinding
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.stateMachine.FullPageViewStateHandler
import com.example.myapplication.presentation.widgets.ErrorView

class WorkOrderListFragment : BaseFragment(), WorkOrderListAdapter.WorkOrderItemAdapterListener, ErrorView.ErrorViewAction {

    private lateinit var workOrderListAdapter: WorkOrderListAdapter

    lateinit var binding: FragmentWorkOrderListBinding

    val viewModel: WorkOrderListViewModel by viewModels()

    companion object {
        const val tag = "WorkOrderListFragment"
        fun newInstance() = WorkOrderListFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkOrderListBinding.inflate(inflater, container, false)
        initObservers()
        initViews()
        return binding.root
    }

    private fun initViews () {
        workOrderListAdapter = WorkOrderListAdapter(listener = this)
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = workOrderListAdapter
        }
        binding.efbRefresh.setOnClickListener {
            viewModel.refresh()
        }
        viewModel.onViewCreated()
    }

    private fun initObservers() {
        FullPageViewStateHandler(binding.superFrameLayout, binding.clRoot).apply {
            setStateMachine(
                viewModel.fullPageStateMachine,
                viewLifecycleOwner,
                this@WorkOrderListFragment
            )
        }
        viewModel.workOrdersLD.observe(viewLifecycleOwner, Observer {
            workOrderListAdapter.setList(it)
        })
    }

    override fun onWorkOrderItemClick(workOrderItemContent: WorkOrderItemContent) {
        val intent = Intent(context, WorkOrderDetailsActivity::class.java)
        intent.putExtra("args_title", workOrderItemContent.title)
        intent.putExtra("args_description", workOrderItemContent.description)
        startActivity(intent)
    }

    override fun onTapToRetry() {
        viewModel.refresh()
    }
}