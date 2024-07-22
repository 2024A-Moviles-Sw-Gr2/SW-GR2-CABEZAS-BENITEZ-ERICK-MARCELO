package com.example.appenmemoria

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ArtistaAdapter(context: Context, artistas: List<BArtista>) : ArrayAdapter<BArtista>(context, 0, artistas) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val artista = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)

        val text1 = view.findViewById<TextView>(android.R.id.text1)
        val text2 = view.findViewById<TextView>(android.R.id.text2)

        text1.text = "${artista?.id} - ${artista?.nombre}"
        text2.text = "Edad: ${artista?.edad}"

        view.tag = artista?.id // Set the artist id as the tag

        return view
    }
}