package com.eventtracker.domain.stores

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Comment
import java.lang.Exception

interface CommentStore {
    fun getComment (id: String): ResultWrapper<Comment?, Exception>
    fun saveComment (comment: Comment): ResultWrapper<Unit, Exception>
}