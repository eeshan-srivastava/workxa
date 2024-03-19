package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.BuildConfig
import com.example.myapplication.presentation.main.WorkApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext applicationContext: Context): WorkApp {
        return applicationContext as WorkApp
    }

    @Provides
    @Singleton
    @Named("base_url")
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

}
