package com.example.data.repository

import android.annotation.SuppressLint
import com.example.data.api.AppApiInterface
import com.example.data.local.db.database.AppDatabase
import com.example.data.local.db.entity.WorkOrderItemEntity
import com.example.data.local.db.entity.toWorkOrderItemContent
import com.example.data.local.db.makeDbCall
import com.example.data.local.pref.PreferenceProvider
import com.example.data.model.toGetWorkOrdersContent
import com.example.data.network.makeApiRequest
import com.example.domain.model.GetWorkOrdersContent
import com.example.domain.model.WorkOrderItemContent
import com.example.domain.repository.WorkOrderRepository
import com.example.utils.onSuccessObservable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class WorkOrderDataRepository @Inject constructor(
    @Named("unauthorized_client")
    private val appApiInterface: AppApiInterface,
    private val preferenceProvider: PreferenceProvider,
    private val appDatabase: AppDatabase
) :  WorkOrderRepository {

    override fun getWorkOrders(): Observable<GetWorkOrdersContent> {
        return makeApiRequest(appApiInterface.getWorkOrders(
        ), { _, data ->
            data.toGetWorkOrdersContent(data)
        }, { emitter, data ->
            emitter.onSuccessObservable(data)
        })
    }

    override fun getStoredWorkOrders(): Observable<GetWorkOrdersContent> {
        return makeDbCall(appDatabase.workOrderDao().getWorkOrders(
        ), { _, data ->
            GetWorkOrdersContent(workOrders = data.map { it -> it.toWorkOrderItemContent(it) })
        }, { emitter, data ->
            emitter.onSuccessObservable(data)
        })
    }

    @SuppressLint("CheckResult")
    override fun storeWorkOrders(workOrders: List<WorkOrderItemContent>): Completable {
        return appDatabase.workOrderDao().insertWorkOrders(workOrders = workOrders.map {
            WorkOrderItemEntity(id=it.id, title = it.title, description = it.description)
        })
    }

    override fun deleteWorkOrders():Completable {
        return  appDatabase.workOrderDao().deleteWorkOrders()
    }

    override fun updateWorkOrders(workOrders: List<WorkOrderItemContent>): Completable {
        return deleteWorkOrders()
            .andThen(Completable.defer { storeWorkOrders(workOrders) })
    }

}
