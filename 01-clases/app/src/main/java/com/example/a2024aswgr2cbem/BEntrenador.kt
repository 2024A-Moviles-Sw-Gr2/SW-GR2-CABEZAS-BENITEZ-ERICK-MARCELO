package com.example.a2024aswgr2cbem

import android.os.Parcel
import android.os.Parcelable
import kotlinx.coroutines.internal.OpDescriptor

class BEntrenador (
    var id:Int,
    var nombre:String,
    var descriptor: String?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()
    ) {
    }

    override fun toString(): String{
        return "$nombre ${descriptor}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(descriptor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}
