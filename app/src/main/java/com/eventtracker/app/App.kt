package com.eventtracker.app

import android.app.Application
import androidx.multidex.MultiDex
import javax.inject.Inject
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.realm.Realm

import com.eventtracker.app.di.components.DaggerAppComponent

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .create(this)
            .build()
            .inject(this)

        MultiDex.install(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private fun initRealm() {
        Realm.init(this)
    }
}