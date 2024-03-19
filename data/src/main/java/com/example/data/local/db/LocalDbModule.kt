package com.example.data.local.db

import android.app.Application
import androidx.room.Room
import com.example.data.local.db.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDb(app:Application): AppDatabase =
        Room.databaseBuilder(app,AppDatabase::class.java,"app_db")
            .build()

}
