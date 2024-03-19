package com.example.myapplication.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBaseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private lateinit var baseBinding: FragmentBaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        baseBinding.lifecycleOwner = this
        return baseBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
