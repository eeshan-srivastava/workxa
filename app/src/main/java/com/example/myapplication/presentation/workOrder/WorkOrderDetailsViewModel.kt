package com.example.myapplication.presentation.workOrder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkOrderDetailsViewModel @Inject constructor(

) : BaseViewModel() {

    private val _titleLD: MutableLiveData<String> = MutableLiveData()
    val titleLD: LiveData<String>
        get() = _titleLD

    private val _descriptionLD: MutableLiveData<String> = MutableLiveData()
    val descriptionLD: LiveData<String>
        get() = _descriptionLD

    fun onViewCreated(title: String, description:String) {
        _titleLD.postValue(title)
        _descriptionLD.postValue(description)
    }

}