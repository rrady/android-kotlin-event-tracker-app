package com.eventtracker.domain.repositories

import java.lang.Exception

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host

interface IHostRepository {
    fun getHosts(): ResultWrapper<List<Host>, Exception>
    fun getHostsFollowedByUser(userId: String): ResultWrapper<List<Host>, Exception>
}