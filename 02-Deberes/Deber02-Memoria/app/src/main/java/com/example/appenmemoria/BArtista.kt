package com.example.appenmemoria

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class BArtista(
    var id:Int,
    var nombre:String,
    var canciones: ArrayList<BCancion>?,
    var edad: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createTypedArrayList(BCancion.CREATOR),
        parcel.readInt()
    ) {
    }

    override fun toString(): String{
        return "$nombre ${nombre}"
    }

    fun agregarCancion(cancion:BCancion):Boolean{
        canciones?.add(cancion)
        return true
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeTypedList(canciones)
        parcel.writeInt(edad)
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