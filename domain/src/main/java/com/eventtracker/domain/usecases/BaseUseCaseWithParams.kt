package com.eventtracker.domain.usecases

abstract class BaseUseCaseWithParams<in Params, out R> {
    protected abstract suspend fun buildUseCase(params: Params) : R
    suspend fun execute(params: Params): R = buildUseCase(params)
}