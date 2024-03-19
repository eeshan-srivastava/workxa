package com.example.data.model

import com.example.domain.model.GetWorkOrdersContent
import com.example.domain.model.WorkOrderItemContent

data class GetWorkOrdersNao(val work_orders: List<WorkOrderItemContent>)

fun GetWorkOrdersNao.toGetWorkOrdersContent(it: GetWorkOrdersNao): GetWorkOrdersContent {
    return  GetWorkOrdersContent(workOrders=it.work_orders)
}
