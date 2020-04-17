package com.eventtracker.app.ui.host

import androidx.lifecycle.ViewModel
import com.eventtracker.domain.models.Host

class HostDetailViewModel(): ViewModel() {
    private lateinit var _host: Host

    var host: Host
        get() = _host
        set(value) { _host = value }
}