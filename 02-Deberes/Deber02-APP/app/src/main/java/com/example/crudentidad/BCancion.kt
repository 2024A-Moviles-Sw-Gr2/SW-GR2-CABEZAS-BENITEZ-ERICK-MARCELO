package com.example.crudentidad

import android.os.Parcel
import android.os.Parcelable

class BCancion(
    var id:Int,
    var nombre:String,
    var tipo: String?,
    var anio: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String{
        return "$nombre ${nombre}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(tipo)
        parcel.writeInt(anio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BCancion> {
        override fun createFromParcel(parcel: Parcel): BCancion {
            return BCancion(parcel)
        }

        override fun newArray(size: Int): Array<BCancion?> {
            return arrayOfNulls(size)
        }
    }

}