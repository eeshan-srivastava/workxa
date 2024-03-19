package com.example.myapplication.presentation.main
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WorkApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}