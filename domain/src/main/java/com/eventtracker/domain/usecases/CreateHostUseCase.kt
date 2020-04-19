package com.eventtracker.domain.usecases

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.repositories.HostRepository

class CreateHostUseCase(private val repository: HostRepository): BaseUseCaseWithParams<Host, ResultWrapper<Unit, Exception>>() {
    override suspend fun buildUseCase(params: Host): ResultWrapper<Unit, Exception> {
        return repository.addHost(params)
    }
}