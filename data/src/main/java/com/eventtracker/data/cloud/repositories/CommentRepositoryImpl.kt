package com.eventtracker.data.cloud.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Reply
import com.eventtracker.domain.repositories.CommentRepository

class CommentRepositoryImpl: CommentRepository {
    override fun addReply(reply: Reply): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getReplies(commentId: String): ResultWrapper<List<Reply>, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}