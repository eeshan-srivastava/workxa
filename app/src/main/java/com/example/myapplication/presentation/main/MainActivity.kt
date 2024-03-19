package com.example.myapplication.presentation.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presentation.base.BaseActivity
import com.example.myapplication.presentation.login.LoginFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(
                R.id.fragmentContainer,
                LoginFragment.newInstance(),
                "LOGIN_FRAGMENT"
            )
        }
    }

}