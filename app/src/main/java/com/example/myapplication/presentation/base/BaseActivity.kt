package com.example.myapplication.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {


    companion object {
        private lateinit var baseBinding: ActivityBaseBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)

    }

    fun attachFragment(fragmentHolderLayoutId: Int, fragment: Fragment?, tag: String?) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()
        if (manager.findFragmentByTag(tag) == null) { // No fragment in backStack with same tag..
            ft.add(fragmentHolderLayoutId, fragment!!, tag)
            ft.addToBackStack(tag)
            ft.commit()
            for (frag in manager.fragments) {
                ft.hide(frag)
            }
        } else {
            for (frag in manager.fragments) {
                ft.hide(frag)
            }
            ft.show(manager.findFragmentByTag(tag)!!).commit()
        }
    }

    open fun getContext(): Context {
        return this
    }

}
