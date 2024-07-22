package com.example.appenmemoria

import java.util.ArrayList

class BDatosMemoriaSong(

) {
    companion object {
        val arregloBCancion = arrayListOf<BCancion>()
        var idsSong=0

        val nuevaCancion = BCancion(7, "erickCabezas", "regueton", 2001,2)

        // Método para crear un nuevo artista
        fun crearCancion(cancion: BCancion): Boolean {
            return arregloBCancion.add(cancion)
        }

        // Método para leer (obtener) todos los artistas
        fun obtenerCanciones(): List<BCancion> {
            return arregloBCancion
        }

        // Método para obtener un artista por su ID
        fun obtenerCancionPorID(id: Int): BCancion? {
            return arregloBCancion.find { it.id == id }
        }

        // Método para actualizar un artista por su ID
        fun actualizarCancion(id: Int, nuevaCancion: BCancion): Boolean {
            val indice = arregloBCancion.indexOfFirst { it.id == id }
            return if (indice != -1) {
                arregloBCancion[indice]= nuevaCancion
                true
            } else {
                false
            }
        }

        // Método para eliminar un artista por su ID
        fun eliminarCancion(id: Int): Boolean {
            val cancion = obtenerCancionPorID(id)
            return if (cancion != null) {
                arregloBCancion.remove(cancion)
                true
            } else {
                false
            }
        }

        fun cancionesPorArtista(idArtista:Int): List<BCancion>{
            val todasLasCanciones = obtenerCanciones() // Esta función debe devolver todas las canciones
            val cancionesDelArtista = todasLasCanciones.filter { it.artistaId == idArtista }
            return cancionesDelArtista
        }
    }
}