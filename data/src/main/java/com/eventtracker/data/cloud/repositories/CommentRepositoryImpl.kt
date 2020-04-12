package com.eventtracker.data.cloud.repositories

import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.QuerySnapshot

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Comment
import com.eventtracker.domain.repositories.CommentRepository

import com.eventtracker.data.cloud.entities.FirebaseComment
import com.eventtracker.data.cloud.utils.awaitTaskCompletable
import com.eventtracker.data.cloud.utils.awaitTaskResult

const val COMMENT_COLLECTION = "comments"

class CommentRepositoryImpl: CommentRepository {
    private val db = Firebase.firestore

    override suspend fun getCommentsForEvent(eventId: String): ResultWrapper<List<Comment>, Exception> {
        var collection = db.collection(COMMENT_COLLECTION).whereEqualTo("eventId", eventId)

        return try {
            val task = awaitTaskResult(collection.get())

            resultToCommentList(task)
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun addComment(comment: Comment): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(COMMENT_COLLECTION)
                .document(comment.id)
                .set(FirebaseComment.fromComment(comment))
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun updateComment(comment: Comment): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(COMMENT_COLLECTION)
                .document(comment.id)
                .update(FirebaseComment.fromComment(comment).toMap())
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun deleteComment(id: String): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(COMMENT_COLLECTION)
                .document(id)
                .delete()
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    private fun resultToCommentList(result: QuerySnapshot?): ResultWrapper<List<Comment>, Exception> {
        val comments = mutableListOf<Comment>()

        result?.forEach { documents ->
            comments.add(documents.toObject<FirebaseComment>().toComment())
        }

        return ResultWrapper.build {
            comments
        }
    }
}