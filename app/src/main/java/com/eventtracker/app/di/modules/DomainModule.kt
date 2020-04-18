package com.eventtracker.app.di.modules

import dagger.Module

import com.eventtracker.app.di.modules.domain.*

@Module(includes = [UseCasesModule::class])
interface DomainModule