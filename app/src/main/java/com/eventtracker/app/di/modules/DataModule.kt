package com.eventtracker.app.di.modules

import dagger.Module

import com.eventtracker.app.di.modules.data.*

@Module(includes = [RealmModule::class, RepositoriesModule::class])
interface DataModule