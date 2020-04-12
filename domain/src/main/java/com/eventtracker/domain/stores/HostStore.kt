package com.eventtracker.domain.stores

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host

interface HostStore {
    fun getHost(id: String): ResultWrapper<Host?, Exception>
    fun saveHost(host: Host): ResultWrapper<Unit, Exception>
}