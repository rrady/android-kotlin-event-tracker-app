package com.eventtracker.app.di.modules.domain

import javax.inject.Singleton
import dagger.Module
import dagger.Provides

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
    fun provideCreateHostUseCase(hostRepository: HostRepository): CreateHostUseCase {
        return CreateHostUseCase(hostRepository)
    }

    @Provides
    @Singleton
    fun provideGetEventsUseCase(eventRepository: EventRepository): GetEventsUseCase {
        return GetEventsUseCase(eventRepository)
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

    @Provides
    @Singleton
    fun provideGetFollowedHostsUseCase(hostRepository: HostRepository): GetFollowedHostsUseCase {
        return GetFollowedHostsUseCase(hostRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteHostUseCase(hostRepository: HostRepository): DeleteHostUseCase {
        return DeleteHostUseCase(hostRepository)
    }
}