package com.eventtracker.app.ui.hostlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.eventtracker.domain.models.Host

class HostViewModel(private val host: Host): ViewModel() {
    private val _name = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()
    private val _avatarUri = MutableLiveData<String>()

    val name: LiveData<String>
        get() = _name

    val description: LiveData<String>
        get() = _description

    val avatarUri: LiveData<String>
        get() = _avatarUri

    init {
        _name.value = host.name
        _description.value = host.description
        _avatarUri.value = host.avatarUri
    }
}