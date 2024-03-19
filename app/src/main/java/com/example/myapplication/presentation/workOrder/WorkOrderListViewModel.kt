package com.example.myapplication.presentation.workOrder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.WorkOrderItemContent
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.WorkOrderUseCase
import com.example.myapplication.presentation.base.BaseViewModel
import com.example.myapplication.presentation.stateMachine.FullPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkOrderListViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val workOrderUseCase: WorkOrderUseCase
) : BaseViewModel() {

    val fullPageStateMachine = MutableLiveData<FullPageState>()

    private val _workOrdersLD: MutableLiveData<List<WorkOrderItemContent>> = MutableLiveData()
    val workOrdersLD: LiveData<List<WorkOrderItemContent>>
        get() = _workOrdersLD

    fun onViewCreated() {
        makeLocalRequest(fullPageStateMachine)
    }

    fun refresh () {
        makeApiRequest(fullPageStateMachine)
    }

    private fun makeApiRequest(stateMachine: MutableLiveData<FullPageState>) {
        stateMachine.postValue(FullPageState.Loading)
        fetchRemoteData(workOrderUseCase.getWorkOrders(),
            {
                it
            }, {
                stateMachine.postValue(FullPageState.Success)
                _workOrdersLD.postValue(it.workOrders)
               updateWorkOrders(workOrders = it.workOrders)
            }
        ) {
            fullPageStateMachine.postValue(FullPageState.Error(it))
        }
    }

    private  fun updateWorkOrders (workOrders:List<WorkOrderItemContent>){
        executeLocalCompletable(workOrderUseCase.deleteWorkOrders(), onSuccess = { executeLocalCompletable(workOrderUseCase.storeWorkOrders(workOrders),{},{})
        }, onError = {})
    }

    private fun makeLocalRequest(stateMachine: MutableLiveData<FullPageState>) {
        stateMachine.postValue(FullPageState.Loading)
        fetchRemoteData(workOrderUseCase.getStoredWorkOrders(),
            {
                it
            }, {
                stateMachine.postValue(FullPageState.Success)
                _workOrdersLD.postValue(it.workOrders)
            }
        ) {
            fullPageStateMachine.postValue(FullPageState.Error(it))
        }
    }

}