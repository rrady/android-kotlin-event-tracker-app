package com.eventtracker.app.di

import javax.inject.Singleton
import dagger.Component

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

}