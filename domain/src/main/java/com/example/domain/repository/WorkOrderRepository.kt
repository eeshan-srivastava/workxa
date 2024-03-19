package com.example.domain.repository

import com.example.domain.model.GetWorkOrdersContent
import com.example.domain.model.WorkOrderItemContent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface WorkOrderRepository {
    fun getWorkOrders(): Observable<GetWorkOrdersContent>
    fun getStoredWorkOrders(): Observable<GetWorkOrdersContent>
    fun storeWorkOrders(workOrders: List<WorkOrderItemContent>): Completable
    fun deleteWorkOrders(): Completable
    fun updateWorkOrders(workOrders: List<WorkOrderItemContent>): Completable
}