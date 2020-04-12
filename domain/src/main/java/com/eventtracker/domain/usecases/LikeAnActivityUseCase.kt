package com.eventtracker.domain.usecases

import com.eventtracker.domain.repositories.HostRepository

import java.lang.Exception
import com.eventtracker.domain.ResultWrapper

class LikeAnActivityUseCase(private val repository: HostRepository): BaseUseCase<String, ResultWrapper<Unit, Exception>>() {
    override suspend fun buildUseCase(params: String): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }
}