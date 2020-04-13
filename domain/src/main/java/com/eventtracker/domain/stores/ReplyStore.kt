package com.eventtracker.domain.stores

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Reply
import java.lang.Exception

interface ReplyStore {
    fun getReply(id: String): ResultWrapper<Reply?, Exception>
    fun saveReply(reply: Reply): ResultWrapper<Unit, Exception>
}