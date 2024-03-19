package com.example.myapplication.presentation.workOrder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityWorkOrdersBinding
import com.example.myapplication.presentation.base.BaseActivity
import com.example.myapplication.presentation.main.MainActivity

class WorkOrdersActivity : BaseActivity() {

    private lateinit var binding: ActivityWorkOrdersBinding
    val viewModel: WorkOrdersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_orders)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(
                R.id.fragmentContainer,
                WorkOrderListFragment.newInstance(),
                "WORK_ORDERS_FRAGMENT"
            )
        }
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init () {
        binding.iAppBar.tvTitle.text="Work Orders"
        binding.iAppBar.bAction.text="Logout"
        binding.iAppBar.bAction.visibility= View.VISIBLE
        binding.iAppBar.ivBack.visibility= View.GONE
        binding.iAppBar.bAction.setOnClickListener {
            viewModel.logout()
            this.finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}