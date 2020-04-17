package com.eventtracker.app.ui.hostlist

import androidx.lifecycle.ViewModel
import com.eventtracker.domain.models.Host
import java.util.*

class HostListViewModel: ViewModel() {
    private val hosts = listOf<Host>(
        Host(UUID.randomUUID().toString(), "Club A", "Club a description", "https://www.gstatic.com/webp/gallery/4.sm.jpg"),
        Host(UUID.randomUUID().toString(), "Club B", "Club b description", "https://www.gstatic.com/webp/gallery/4.sm.jpg"),
        Host(UUID.randomUUID().toString(), "Club C", "Club c description", "https://www.gstatic.com/webp/gallery/4.sm.jpg")
    )

    fun getHosts(): List<Host> {
        return hosts
    }

    fun searchHosts(term: String?): List<Host> {
        return if (term == null || term.isEmpty()) {
            hosts
        } else {
            val resultList = mutableListOf<Host>()
            for (host in hosts) {
                if (host.name.toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT))) {
                    resultList.add(host)
                }
            }
            resultList
        }
    }
}