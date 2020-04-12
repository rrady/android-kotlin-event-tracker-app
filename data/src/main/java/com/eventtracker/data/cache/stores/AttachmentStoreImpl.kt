package com.eventtracker.data.cache.stores

import javax.inject.Inject
import io.realm.kotlin.where
import io.realm.Realm

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Attachment
import com.eventtracker.domain.stores.AttachmentStore

import com.eventtracker.data.cache.entities.RealmAttachment

class AttachmentStoreImpl @Inject constructor(private val realm: Realm): AttachmentStore {
    override fun getAttachment(id: String): ResultWrapper<Attachment?, Exception> {
        return try {
            var result: RealmAttachment? = realm.where<RealmAttachment>()
                                                .equalTo("id", id)
                                                .findFirst()

            ResultWrapper.build { result?.toAttachment() }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override fun saveAttachment(t: Attachment): ResultWrapper<Unit, Exception> {
        return try {
            realm.beginTransaction()
            var result: RealmAttachment? = realm.where<RealmAttachment>()
                                                .equalTo("id", t.id)
                                                .findFirst()
            result?.deleteFromRealm()
            result = RealmAttachment.fromAttachment(t)
            realm.insert(result)
            realm.commitTransaction()

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }
}