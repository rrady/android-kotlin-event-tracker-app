package com.eventtracker.domain.usecases

abstract class BaseUseCase<out R> {
    protected abstract suspend fun buildUseCase() : R
    suspend fun execute(): R = buildUseCase()
}