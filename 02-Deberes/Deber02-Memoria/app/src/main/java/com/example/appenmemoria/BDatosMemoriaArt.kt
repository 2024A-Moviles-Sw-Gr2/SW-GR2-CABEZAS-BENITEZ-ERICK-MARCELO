package com.example.appenmemoria

import java.util.ArrayList

class BDatosMemoriaArt(

) {
    companion object {
        val arregloBArtista = arrayListOf<BArtista>()
        var idsArt=0

        // Método para crear un nuevo artista
        fun crearArtista(artista: BArtista): Boolean {
            return arregloBArtista.add(artista)
        }

        // Método para leer (obtener) todos los artistas
        fun obtenerArtistas(): List<BArtista> {
            return arregloBArtista
        }

        // Método para obtener un artista por su ID
        fun obtenerArtistaPorId(id: Int): BArtista? {
            return arregloBArtista.find { it.id == id }
        }

        // Método para actualizar un artista por su ID
        fun actualizarArtista(id: Int, nuevoArtista: BArtista): Boolean {
            val indice = arregloBArtista.indexOfFirst { it.id == id }
            if (indice != -1) {
                arregloBArtista[indice].nombre = nuevoArtista.nombre
                arregloBArtista[indice].edad=nuevoArtista.edad
                return true
            } else {
                return false
            }
        }

        // Método para eliminar un artista por su ID
        fun eliminarArtista(id: Int): Boolean {
            val artista = obtenerArtistaPorId(id)
            return if (artista != null) {
                arregloBArtista.remove(artista)
                true
            } else {
                false
            }
        }
    }
}