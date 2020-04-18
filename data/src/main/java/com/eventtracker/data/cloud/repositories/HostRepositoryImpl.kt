package com.eventtracker.data.cloud.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.repositories.HostRepository

const val HOST_COLLECTION = "hosts"

class HostRepositoryImpl: HostRepository {
    override suspend fun getHosts(): ResultWrapper<List<Host>, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun getHost(id: String): ResultWrapper<Host?, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun addHost(host: Host): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun updateHost(host: Host): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteHost(id: String): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }
}