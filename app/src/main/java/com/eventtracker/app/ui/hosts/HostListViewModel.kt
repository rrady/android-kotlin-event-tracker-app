package com.eventtracker.app.ui.hosts

import androidx.lifecycle.ViewModel
import com.eventtracker.domain.models.Host
import java.util.*

class HostListViewModel: ViewModel() {
    private val hosts = listOf<Host>(
        Host(UUID.randomUUID().toString(), "Club A", "Club a description", "http://i.imgur.com/DvpvklR.png"),
        Host(UUID.randomUUID().toString(), "Club B", "Club b description", "http://i.imgur.com/DvpvklR.png"),
        Host(UUID.randomUUID().toString(), "Club C", "Club c description", "http://i.imgur.com/DvpvklR.png")
    )

    fun getHosts(): List<Host> {
        return hosts;
    }
}