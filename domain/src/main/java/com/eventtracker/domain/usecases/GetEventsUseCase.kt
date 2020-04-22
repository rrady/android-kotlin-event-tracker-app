package com.eventtracker.domain.usecases

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event
import com.eventtracker.domain.repositories.EventRepository

class GetEventsUseCase(private val repository: EventRepository): BaseUseCaseWithParams<String, ResultWrapper<List<Event>, Exception>>() {
    override suspend fun buildUseCase(params: String): ResultWrapper<List<Event>, Exception> {
        return repository.getEventsForHost(params)
    }
}