package com.example.data.network

import com.example.data.repository.AuthDataRepository
import com.example.data.repository.WorkOrderDataRepository
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.WorkOrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideWorkOrderDataRepository(workOrderDataRepository: WorkOrderDataRepository): WorkOrderRepository {
        return workOrderDataRepository
    }

    @Singleton
    @Provides
    fun provideAuthDataRepository(authDataRepository: AuthDataRepository): AuthRepository {
        return authDataRepository
    }

}

