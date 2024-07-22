package com.example.crudentidad

import android.os.Parcel
import android.os.Parcelable

class BArtista(
    var id:Int,
    var nombre:String,
    var canciones: String?,
    var nacimiento: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!
    ) {
    }

    override fun toString(): String{
        return "$nombre ${nombre}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(canciones)
        parcel.writeString(nacimiento)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BArtista> {
        override fun createFromParcel(parcel: Parcel): BArtista {
            return BArtista(parcel)
        }

        override fun newArray(size: Int): Array<BArtista?> {
            return arrayOfNulls(size)
        }
    }

}