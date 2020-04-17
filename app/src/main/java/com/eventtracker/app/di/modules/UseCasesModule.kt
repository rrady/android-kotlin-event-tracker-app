package com.eventtracker.app.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

import com.eventtracker.domain.repositories.HostRepository
import com.eventtracker.domain.usecases.GetHostsUseCase

@Module
class UseCasesModule {
    @Provides
    @Singleton
    fun provideGetHostsUseCase(hostRepository: HostRepository): GetHostsUseCase {
        return GetHostsUseCase(hostRepository)
    }
}