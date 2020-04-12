package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Reply

interface ReplyRepository {
    suspend fun getRepliesForCommentId(id: String): ResultWrapper<List<Reply>, Exception>
    suspend fun addReply(reply: Reply): ResultWrapper<Unit, Exception>
    suspend fun updateReply(reply: Reply): ResultWrapper<Unit, Exception>
    suspend fun deleteReply(id: String): ResultWrapper<Unit, Exception>
}