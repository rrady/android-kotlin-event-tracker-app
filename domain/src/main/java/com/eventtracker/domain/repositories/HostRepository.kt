package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host

interface HostRepository {
    fun getHosts(): ResultWrapper<List<Host>, Exception>
    fun getHost(id: String): ResultWrapper<Host?, Exception>
    fun addHost(host: Host): ResultWrapper<Unit, Exception>
    fun updateHost(host: Host): ResultWrapper<Unit, Exception>
    fun deleteHost(id: String): ResultWrapper<Unit, Exception>
}