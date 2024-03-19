package com.example.myapplication.presentation.workOrder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityWorkOrderDetailsBinding
import com.example.myapplication.presentation.base.BaseActivity

class WorkOrderDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityWorkOrderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_order_details)
        val intent : Intent = getIntent();
        val title : String = intent.getStringExtra("args_title").toString()
        val description : String = intent.getStringExtra("args_description").toString()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(
                R.id.fragmentContainer,
                WorkOrderDetailsFragment.newInstance(title,description),
                "WORK_ORDER_DETAILS_FRAGMENT"
            )
        }
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init () {
        binding.iAppBar.tvTitle.text="Order Details"
        binding.iAppBar.ivBack.visibility= View.VISIBLE
        binding.iAppBar.ivBack.setOnClickListener {
            this.finish()
        }
    }

}