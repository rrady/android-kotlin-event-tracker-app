package com.eventtracker.app.di.modules.data

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

import com.eventtracker.domain.repositories.*
import com.eventtracker.data.cloud.repositories.*

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideHostRepository(): HostRepository {
        return HostRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideEventRepository(): EventRepository {
        return EventRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLocationRepository(): LocationRepository {
        return LocationRepositoryImpl()
    }
}