package com.eventtracker.domain

sealed class ResultWrapper<out V, out E> {

    data class Success<out V>(val value: V) : ResultWrapper<V, Nothing>()
    data class Error<out E>(val error: E) : ResultWrapper<Nothing, E>()

    companion object Factory {
        inline fun <V> build(function: () -> V): ResultWrapper<V, Exception> =
            try {
                Success(function.invoke())
            } catch (e: java.lang.Exception) {
                Error(e)
            }
    }
}