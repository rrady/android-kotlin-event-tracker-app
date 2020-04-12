package com.eventtracker.data.cloud.repositories

import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.QuerySnapshot

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Attachment
import com.eventtracker.domain.repositories.AttachmentRepository

import com.eventtracker.data.cloud.entities.FirebaseAttachment
import com.eventtracker.data.cloud.utils.awaitTaskCompletable
import com.eventtracker.data.cloud.utils.awaitTaskResult

const val ATTACHMENT_COLLECTION = "attachments"

class AttachmentRepositoryImpl: AttachmentRepository {
    private val db = Firebase.firestore

    override suspend fun getAttachment(id: String): ResultWrapper<Attachment?, Exception> {
        var document = db.collection(ATTACHMENT_COLLECTION).document(id)

        return try {
            val task = awaitTaskResult(document.get())

            ResultWrapper.build {
                task.toObject<FirebaseAttachment>()?.toAttachment()
            }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun addAttachment(attachment: Attachment): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(ATTACHMENT_COLLECTION)
                .document(attachment.id)
                .set(FirebaseAttachment.fromAttachment(attachment))
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun updateAttachment(attachment: Attachment): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(ATTACHMENT_COLLECTION)
                .document(attachment.id)
                .update(FirebaseAttachment.fromAttachment(attachment).toMap())
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun deleteAttachment(id: String): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(ATTACHMENT_COLLECTION)
                .document(id)
                .delete()
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }
}