package com.example.data.local.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.db.dao.WorkOrderDao
import com.example.data.local.db.entity.WorkOrderItemEntity

@Database(entities = [WorkOrderItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract fun workOrderDao(): WorkOrderDao

}