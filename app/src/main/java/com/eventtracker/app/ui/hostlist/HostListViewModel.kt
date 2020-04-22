package com.eventtracker.app.ui.hostlist

import java.util.*
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import com.eventtracker.domain.models.Host
import com.eventtracker.domain.usecases.GetHostsUseCase

class HostListViewModel @Inject constructor(private val useCase: GetHostsUseCase): ViewModel(), CoroutineScope {
    private var _hosts = MutableLiveData<List<Host>>()

    val hosts: LiveData<List<Host>>
        get() = _hosts

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main


    fun initHosts() = launch {
        var newHosts = useCase.execute()
        _hosts.value = newHosts
    }

    fun searchHosts(term: String?): List<Host> {
        return if (term == null || term.isEmpty()) {
            _hosts.value?: listOf()
        } else {
            val resultList = mutableListOf<Host>()
            for (host in _hosts.value!!) {
                if (host.name.toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT))) {
                    resultList.add(host)
                }
            }
            resultList
        }
    }
}