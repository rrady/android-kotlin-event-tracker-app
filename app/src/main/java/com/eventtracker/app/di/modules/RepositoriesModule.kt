package com.eventtracker.app.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

import com.eventtracker.data.cloud.repositories.HostRepositoryImpl
import com.eventtracker.domain.repositories.HostRepository

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideHostRepository(): HostRepository {
        return HostRepositoryImpl()
    }
}