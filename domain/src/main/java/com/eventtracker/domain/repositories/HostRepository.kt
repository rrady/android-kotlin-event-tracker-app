package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host

interface HostRepository {
    suspend fun getHosts(): ResultWrapper<List<Host>, Exception>
    suspend fun getHost(id: String): ResultWrapper<Host?, Exception>
    suspend fun addHost(host: Host): ResultWrapper<Unit, Exception>
    suspend fun updateHost(host: Host): ResultWrapper<Unit, Exception>
    suspend fun deleteHost(id: String): ResultWrapper<Unit, Exception>
}