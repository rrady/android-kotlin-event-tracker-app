package com.eventtracker.app.di.modules

import dagger.Module

import com.eventtracker.app.di.modules.ui.*

@Module(includes = [ActivitiesModule::class, FragmentsModule::class, ViewModelsModule::class])
interface UiModule