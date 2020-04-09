package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Reply

interface CommentRepository {
    fun addReply(reply: Reply): String
    fun getReplies(commentId: String): ResultWrapper<List<Reply>, Exception>
}