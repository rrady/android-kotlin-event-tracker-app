package com.eventtracker.domain.usecases

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.repositories.HostRepository

class GetFollowedHostsUseCase(private val repository: HostRepository): BaseUseCase<List<Host>>() {
    override suspend fun buildUseCase(): List<Host> {
        var result = repository.getHosts()
        return when (result) {
            is ResultWrapper.Success -> result.value
            is ResultWrapper.Error -> throw result.error
        }
    }
}