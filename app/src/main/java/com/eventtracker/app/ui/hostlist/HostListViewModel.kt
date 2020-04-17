package com.eventtracker.app.ui.hostlist

import java.util.*
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.eventtracker.domain.models.Host
import com.eventtracker.domain.usecases.GetHostsUseCase

class HostListViewModel  @Inject constructor(private val useCase: GetHostsUseCase): ViewModel() {
    private var _hosts = mutableListOf<Host>()

    val hosts: List<Host>
        get() = _hosts

    fun initHosts() {
        viewModelScope.launch {
            _hosts = useCase.execute() as MutableList<Host>
        }
    }

    fun searchHosts(term: String?): List<Host> {
        return if (term == null || term.isEmpty()) {
            _hosts
        } else {
            val resultList = mutableListOf<Host>()
            for (host in _hosts) {
                if (host.name.toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT))) {
                    resultList.add(host)
                }
            }
            resultList
        }
    }
}