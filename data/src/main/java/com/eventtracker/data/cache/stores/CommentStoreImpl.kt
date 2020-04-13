package com.eventtracker.data.cache.stores

import io.realm.Realm
import io.realm.kotlin.where
import javax.inject.Inject

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Comment
import com.eventtracker.domain.stores.CommentStore

import com.eventtracker.data.cache.entities.RealmComment

class CommentStoreImpl @Inject constructor(private val realm: Realm): CommentStore {
    override fun getComment(id: String): ResultWrapper<Comment?, Exception> {
        return try {
            var result: RealmComment? = realm.where<RealmComment>()
                                             .equalTo("id", id)
                                             .findFirst()

            ResultWrapper.build { result?.toComment() }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override fun saveComment(t: Comment): ResultWrapper<Unit, Exception> {
        return try {
            realm.beginTransaction()
            var result: RealmComment? = realm.where<RealmComment>()
                .equalTo("id", t.id)
                .findFirst()
            result?.deleteFromRealm()
            result = RealmComment.fromComment(t)
            realm.insert(result)
            realm.commitTransaction()

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }
}