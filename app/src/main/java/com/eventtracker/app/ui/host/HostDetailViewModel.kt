package com.eventtracker.app.ui.host

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

import com.eventtracker.domain.models.Host
import com.eventtracker.domain.usecases.DeleteHostUseCase

class HostDetailViewModel @Inject constructor(private val useCase: DeleteHostUseCase): ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    private lateinit var _host: Host

    var host: Host
        get() = _host
        set(value) { _host = value }

    fun delete() = launch {
        useCase.execute(_host.id)
    }
}