package com.eventtracker.data.cache.stores

import javax.inject.Inject
import io.realm.kotlin.where
import io.realm.Realm

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Location
import com.eventtracker.domain.stores.LocationStore

import com.eventtracker.data.cache.entities.RealmLocation

class LocationStoreImpl @Inject constructor(private val realm: Realm): LocationStore {
    override fun getLocation(id: String): ResultWrapper<Location?, Exception> {
        return try {
            var result: RealmLocation? = realm.where<RealmLocation>()
                .equalTo("id", id)
                .findFirst()

            ResultWrapper.build { result?.toLocation() }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override fun saveLocation(t: Location): ResultWrapper<Unit, Exception> {
        return try {
            realm.beginTransaction()
            var result: RealmLocation? = realm.where<RealmLocation>()
                .equalTo("id", t.id)
                .findFirst()
            result?.deleteFromRealm()
            result = RealmLocation.fromLocation(t)
            realm.insert(result)
            realm.commitTransaction()

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }
}