package com.eventtracker.data.cache.stores

import javax.inject.Inject
import io.realm.kotlin.where
import io.realm.Realm

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event
import com.eventtracker.domain.stores.EventStore

import com.eventtracker.data.cache.entities.RealmEvent

class EventStoreImpl @Inject constructor(private val realm: Realm): EventStore {
    override fun getEvent(id: String): ResultWrapper<Event?, Exception> {
        return try {
            var result: RealmEvent? = realm.where<RealmEvent>()
                .equalTo("id", id)
                .findFirst()

            ResultWrapper.build { result?.toEvent() }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override fun saveEvent(t: Event): ResultWrapper<Unit, Exception> {
        return try {
            realm.beginTransaction()
            var result: RealmEvent? = realm.where<RealmEvent>()
                .equalTo("id", t.id)
                .findFirst()
            result?.deleteFromRealm()
            result = RealmEvent.fromEvent(t)
            realm.insert(result)
            realm.commitTransaction()

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }
}