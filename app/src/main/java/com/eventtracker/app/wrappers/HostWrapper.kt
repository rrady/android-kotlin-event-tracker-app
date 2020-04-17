package com.eventtracker.app.wrappers

import android.os.Parcel
import android.os.Parcelable

import com.eventtracker.domain.models.Host

class HostWrapper(private val _host: Host): Parcelable {
    constructor(parcel: Parcel) : this(
        Host(parcel.readString()!!, parcel.readString()!!, parcel.readString()!!, parcel.readString()!!,
            parcel.readString()!!, parcel.readString()!!, parcel.readString()!!, parcel.readString()!!)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_host.id)
        parcel.writeString(_host.name)
        parcel.writeString(_host.description)
        parcel.writeString(_host.avatarUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    val host: Host
        get() = _host

    companion object CREATOR : Parcelable.Creator<HostWrapper> {
        override fun createFromParcel(parcel: Parcel): HostWrapper {
            return HostWrapper(parcel)
        }

        override fun newArray(size: Int): Array<HostWrapper?> {
            return arrayOfNulls(size)
        }
    }
}