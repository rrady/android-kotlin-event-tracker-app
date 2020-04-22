package com.eventtracker.domain.usecases

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.repositories.HostRepository

class DeleteHostUseCase(private val repository: HostRepository): BaseUseCaseWithParams<String, ResultWrapper<Unit, Exception>>() {
    override suspend fun buildUseCase(params: String): ResultWrapper<Unit, Exception> {
        return repository.deleteHost(params)
    }
}