package com.example.appenmemoria

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CancionAdapter(context: Context, canciones: List<BCancion>) : ArrayAdapter<BCancion>(context, 0, canciones) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val cancion = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)

        if(cancion!=null) {
            val text1 = view.findViewById<TextView>(android.R.id.text1)
            val text2 = view.findViewById<TextView>(android.R.id.text2)

            text1.text = "${cancion?.id} - ${cancion?.nombre}"
            text2.text = "Tipo: ${cancion?.tipo}, Año: ${cancion?.anio}"

            view.tag = CancionTag(cancion?.id, cancion?.artistaId)
        }

        return view
    }
}