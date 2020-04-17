package com.eventtracker.app.ui.host

import androidx.lifecycle.ViewModel

class EventListViewModel: ViewModel() {
    private lateinit var _hostId: String

    var hostId: String
        get() = _hostId
        set(value) { _hostId = value }
}