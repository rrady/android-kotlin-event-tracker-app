package com.eventtracker.app.di.components

import javax.inject.Singleton
import android.app.Application
import dagger.Component
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.AndroidInjectionModule

import com.eventtracker.app.App
import com.eventtracker.app.di.modules.MainActivityModule
import com.eventtracker.app.di.modules.RepositoriesModule
import com.eventtracker.app.di.modules.UseCasesModule
import com.eventtracker.app.di.modules.ViewModelsModule

@Singleton
@Component(modules = [AndroidInjectionModule::class, MainActivityModule::class, ViewModelsModule::class, UseCasesModule::class, RepositoriesModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }
}