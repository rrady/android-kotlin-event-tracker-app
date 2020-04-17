package com.eventtracker.app

import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm

import com.eventtracker.app.di.components.DaggerAppComponent

class App : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        initRealm()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .create(this)
            .build()
    }

    private fun initRealm() {
        Realm.init(this)
    }
}