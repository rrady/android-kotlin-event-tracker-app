package com.eventtracker.app.ui.hostdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eventtracker.domain.models.Event

class EventViewModel(private val event: Event): ViewModel() {
    private val _name = MutableLiveData<String>()
    private val _photoUri = MutableLiveData<String>()
    private val _beginDate = MutableLiveData<String>()

    val name: LiveData<String>
        get() = _name

    val photoUri: LiveData<String>
        get() = _photoUri

    val beginDate: LiveData<String>
        get() = _beginDate

    init {
        _name.value = event.name
        _photoUri.value = event.photoUri
        _beginDate.value = event.beginDate.toString()
    }
}