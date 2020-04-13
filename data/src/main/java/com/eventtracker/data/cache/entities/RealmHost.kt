package com.eventtracker.data.cache.entities

import com.eventtracker.domain.models.Host
import io.realm.annotations.PrimaryKey
import io.realm.RealmObject

open class RealmHost (
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var avatarUri: String = ""
): RealmObject() {
    fun toHost(): Host {
        return Host(id, name, description, avatarUri)
    }

    companion object {
        fun fromHost(host: Host): RealmHost {
            return RealmHost(host.id, host.name, host.description, host.avatarUri)
        }
    }
}