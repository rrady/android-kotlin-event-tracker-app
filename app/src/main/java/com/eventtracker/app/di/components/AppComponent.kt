package com.eventtracker.app.di.components

import javax.inject.Singleton
import android.app.Application
import dagger.Component
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.AndroidInjectionModule

import com.eventtracker.app.App
import com.eventtracker.app.di.modules.UiModule
import com.eventtracker.app.di.modules.DataModule
import com.eventtracker.app.di.modules.DomainModule

@Singleton
@Component(modules = [AndroidInjectionModule::class, UiModule::class, DataModule::class, DomainModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }
}