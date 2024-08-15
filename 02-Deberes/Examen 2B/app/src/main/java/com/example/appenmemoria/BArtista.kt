package com.example.appenmemoria

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class BArtista(
    var id:Int,
    var nombre:String,
    var edad: Int,
    var latitud: Double,
    var longitud: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun toString(): String{
        return "$nombre ${nombre}"
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeInt(edad)
        parcel.writeDouble(latitud)
        parcel.writeDouble(longitud)
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