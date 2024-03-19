package com.example.myapplication.presentation.workOrder

import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.WorkOrderUseCase
import com.example.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkOrdersViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val workOrderUseCase: WorkOrderUseCase
) : BaseViewModel() {

    fun logout() {
        authUseCase.logout()
        executeLocalCompletable(workOrderUseCase.deleteWorkOrders(), onSuccess = {}, onError = {})
    }

}
