package com.example.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.WorkOrderItemContent
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "work_order")
data class WorkOrderItemEntity(
    @PrimaryKey(autoGenerate = false) var id:String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("description")
    @Expose
    val description: String
):Serializable

fun WorkOrderItemEntity.toWorkOrderItemContent(it: WorkOrderItemEntity): WorkOrderItemContent {
    return  WorkOrderItemContent(id=it.id, title=it.title,description=it.description)
}