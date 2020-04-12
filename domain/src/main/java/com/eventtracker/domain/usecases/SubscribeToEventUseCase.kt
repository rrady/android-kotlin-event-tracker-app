package com.eventtracker.domain.usecases

import java.lang.Exception
import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.repositories.HostRepository

class SubscribeToEventUseCase(private val repository: HostRepository): BaseUseCase<String, ResultWrapper<Unit, Exception>>() {
    override suspend fun buildUseCase(params: String): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }
}