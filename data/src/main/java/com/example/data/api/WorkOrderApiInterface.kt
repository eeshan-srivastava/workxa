package com.example.data.api


import com.example.data.model.GetWorkOrdersNao
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface WorkOrderApiInterface {
    @GET("work-orders.json")
    fun getWorkOrders(
    ): Observable<GetWorkOrdersNao>
}
