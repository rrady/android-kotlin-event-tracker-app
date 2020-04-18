package com.eventtracker.app.di.modules.domain

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

import com.eventtracker.domain.repositories.*
import com.eventtracker.domain.usecases.*

@Module
class UseCasesModule {
    @Provides
    @Singleton
    fun provideGetHostsUseCase(hostRepository: HostRepository): GetHostsUseCase {
        return GetHostsUseCase(hostRepository)
    }

    @Provides
    @Singleton
    fun provideGetEventsUseCase(hostRepository: HostRepository): GetEventsUseCase {
        return GetEventsUseCase(hostRepository)
    }

    @Provides
    @Singleton
    fun provideGetEventUseCase(hostRepository: HostRepository): GetEventUseCase {
        return GetEventUseCase(hostRepository)
    }

    @Provides
    @Singleton
    fun provideNewHostUseCase(hostRepository: HostRepository): NewHostUseCase {
        return NewHostUseCase(hostRepository)
    }

    @Provides
    @Singleton
    fun provideNewEventUseCase(hostRepository: HostRepository): NewEventUseCase {
        return NewEventUseCase(hostRepository)
    }

    @Provides
    @Singleton
    fun provideFollowHostUseCase(hostRepository: HostRepository): FollowHostUseCase {
        return FollowHostUseCase(hostRepository)
    }

    @Provides
    @Singleton
    fun provideAttendEventUseCase(hostRepository: HostRepository): AttendEventUseCase {
        return AttendEventUseCase(hostRepository)
    }
}