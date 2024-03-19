package com.example.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.db.entity.WorkOrderItemEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface WorkOrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkOrders(workOrders: List<WorkOrderItemEntity>): Completable

    @Query("DELETE FROM work_order")
    fun deleteWorkOrders():Completable

    @Query("SELECT * FROM work_order")
    fun getWorkOrders(): Observable<List<WorkOrderItemEntity>>

}
