package com.eventtracker.app.di.components

import android.app.Application
import javax.inject.Singleton
import dagger.Component
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.AndroidInjectionModule

import com.eventtracker.app.App

@Singleton
@Component(modules = [AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}