package com.eventtracker.app

import android.app.Application
import androidx.multidex.MultiDex

import com.eventtracker.app.di.AppComponent
import com.eventtracker.app.di.DaggerAppComponent

class App : Application() {
    private lateinit var app: AppComponent

    override fun onCreate() {
        super.onCreate()
        app = DaggerAppComponent.create()
        MultiDex.install(this)
    }
}