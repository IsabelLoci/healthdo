package com.example.calorietrackerapp

import android.app.Application
import com.example.calorietrackerapp.data.AppContainer
import com.example.calorietrackerapp.data.DefaultAppContainer

class CalorieTrackerApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}