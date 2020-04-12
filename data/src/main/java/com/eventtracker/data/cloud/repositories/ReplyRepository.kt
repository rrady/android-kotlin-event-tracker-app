package com.eventtracker.data.cloud.repositories

import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.QuerySnapshot

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Reply
import com.eventtracker.domain.repositories.ReplyRepository

import com.eventtracker.data.cloud.entities.FirebaseReply
import com.eventtracker.data.cloud.utils.awaitTaskCompletable
import com.eventtracker.data.cloud.utils.awaitTaskResult

const val REPLY_COLLECTION = "replies"

class ReplyRepository: ReplyRepository {
    private val db = Firebase.firestore

    override suspend fun getRepliesForCommentId(id: String): ResultWrapper<List<Reply>, Exception> {
        var collection = db.collection(REPLY_COLLECTION)

        return try {
            val task = awaitTaskResult(collection.get())

            return resultToReplyList(task)
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun addReply(reply: Reply): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(REPLY_COLLECTION)
                .document(reply.id)
                .set(FirebaseReply.fromReply(reply))
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun updateReply(reply: Reply): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(REPLY_COLLECTION)
                .document(reply.id)
                .update(FirebaseReply.fromReply(reply).toMap())
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun deleteReply(id: String): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(REPLY_COLLECTION)
                .document(id)
                .delete()
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    private fun resultToReplyList(result: QuerySnapshot?): ResultWrapper<List<Reply>, Exception> {
        val replies = mutableListOf<Reply>()

        result?.forEach { document ->
            replies.add(document.toObject<FirebaseReply>().toReply())
        }

        return ResultWrapper.build {
            replies
        }
    }
}