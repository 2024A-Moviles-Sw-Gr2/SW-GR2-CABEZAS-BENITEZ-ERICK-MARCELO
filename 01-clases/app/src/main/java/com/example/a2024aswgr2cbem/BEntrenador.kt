package com.example.a2024aswgr2cbem

import kotlinx.coroutines.internal.OpDescriptor

class BEntrenador (
    var id:Int,
    var nombre:String,
    var descriptor: String?
){
    override fun toString(): String{
        return "$nombre ${descriptor}"
    }

}
