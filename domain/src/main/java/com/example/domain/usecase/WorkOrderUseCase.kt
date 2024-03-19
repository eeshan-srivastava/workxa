package com.example.domain.usecase

import com.example.domain.model.GetWorkOrdersContent
import com.example.domain.model.WorkOrderItemContent
import com.example.domain.repository.WorkOrderRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class WorkOrderUseCase @Inject constructor(private val workOrderRepository: WorkOrderRepository) {

    fun getWorkOrders(): Observable<GetWorkOrdersContent> {
        return  workOrderRepository.getWorkOrders()
    }

    fun getStoredWorkOrders(): Observable<GetWorkOrdersContent> {
        return  workOrderRepository.getStoredWorkOrders()
    }

    fun updateWorkOrders(workOrders: List<WorkOrderItemContent>): Completable{
        return  workOrderRepository.updateWorkOrders(workOrders = workOrders)
    }

    fun storeWorkOrders(workOrders: List<WorkOrderItemContent>): Completable{
        return  workOrderRepository.storeWorkOrders(workOrders = workOrders)
    }

    fun deleteWorkOrders(): Completable{
        return  workOrderRepository.deleteWorkOrders()
    }

}
