package com.eventtracker.domain.usecases

import java.lang.Exception
import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.repositories.IHostRepository

class CommentToEventUseCase(private val repository: IHostRepository): BaseUseCase<String, ResultWrapper<Unit, Exception>>() {
    override suspend fun buildUseCase(params: String): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }
}