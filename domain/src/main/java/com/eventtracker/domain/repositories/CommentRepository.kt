package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Comment

interface CommentRepository {
    suspend fun getCommentsForEvent(eventId: String): ResultWrapper<List<Comment>, Exception>
    suspend fun addComment(comment: Comment): ResultWrapper<Unit, Exception>
    suspend fun updateComment(comment: Comment): ResultWrapper<Unit, Exception>
    suspend fun deleteComment(id: String): ResultWrapper<Unit, Exception>
}