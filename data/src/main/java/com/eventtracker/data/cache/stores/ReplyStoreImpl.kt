package com.eventtracker.data.cache.stores

import javax.inject.Inject
import io.realm.kotlin.where
import io.realm.Realm

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Reply
import com.eventtracker.domain.stores.ReplyStore

import com.eventtracker.data.cache.entities.RealmReply

class ReplyStoreImpl @Inject constructor(private val realm: Realm): ReplyStore {
    override fun getReply(id: String): ResultWrapper<Reply?, Exception> {
        return try {
            var result: RealmReply? = realm.where<RealmReply>()
                .equalTo("id", id)
                .findFirst()

            ResultWrapper.build { result?.toReply() }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override fun saveReply(t: Reply): ResultWrapper<Unit, Exception> {
        return try {
            realm.beginTransaction()
            var result: RealmReply? = realm.where<RealmReply>()
                .equalTo("id", t.id)
                .findFirst()
            result?.deleteFromRealm()
            result = RealmReply.fromReply(t)
            realm.insert(result)
            realm.commitTransaction()

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }
}