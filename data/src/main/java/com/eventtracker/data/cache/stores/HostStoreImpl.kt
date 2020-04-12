package com.eventtracker.data.cache.stores

import javax.inject.Inject
import io.realm.kotlin.where
import io.realm.Realm

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.stores.HostStore

import com.eventtracker.data.cache.entities.RealmHost

class HostStoreImpl @Inject constructor(private val realm: Realm): HostStore {
    override fun getHost(id: String): ResultWrapper<Host?, Exception> {
        return try {
            var result: RealmHost? = realm.where<RealmHost>()
                .equalTo("id", id)
                .findFirst()

            ResultWrapper.build { result?.toHost() }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override fun saveHost(t: Host): ResultWrapper<Unit, Exception> {
        return try {
            realm.beginTransaction()
            var result: RealmHost? = realm.where<RealmHost>()
                .equalTo("id", t.id)
                .findFirst()
            result?.deleteFromRealm()
            result = RealmHost.fromHost(t)
            realm.insert(result)
            realm.commitTransaction()

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }
}