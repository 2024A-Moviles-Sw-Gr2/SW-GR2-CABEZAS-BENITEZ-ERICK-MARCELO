package org.example

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class Artista(
    val nombreArtistico: String,
    val fechaDeNacimiento: Date,
    var retirado: Boolean,
    var edad: Int,
    val estatura: Double,
    var canciones: ArrayList<Cancion>
) {
    init {

    }
    companion object {
        val filePath = "src/main/resources/artista.txt"
        fun createFileArtista(filePath: String) {
            val file = File(filePath)
            if (file.createNewFile()) {
                println("Archivo creado: ${file.name}")
            } else {
                println("Archivo existente.")
            }
        }

        ///CRUD ARTISTAS

        //Escribir Artista
        fun writeToFileArtista(artista: Artista) {
            val file = File(Artista.filePath)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val cancionesString = artista.canciones.map { it.nombre }.joinToString(separator = ";")
            file.appendText("${artista.nombreArtistico},${dateFormat.format(artista.fechaDeNacimiento)},${artista.retirado},${artista.edad},${artista.estatura},$cancionesString\n")
            println("Artista ${artista.nombreArtistico} ingresadas al archivo")
        }

        //Leer Artista
        fun readFromFileArtista(): List<Artista> {
            val file = File(Artista.filePath)
            if (!file.exists()) {
                return emptyList()
            }
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            return file.readLines().map { line ->
                val parts = line.split(",")
                val nombreArtistico = parts[0]
                val fechaDeNacimiento = dateFormat.parse(parts[1])
                val retirado = parts[2].toBoolean()
                val edad = parts[3].toInt()
                val estatura = parts[4].toDouble()
                val canciones = parts[5].split(";").map { Cancion.BuscarCancion(it) }
                Artista(nombreArtistico, fechaDeNacimiento, retirado, edad, estatura, ArrayList(canciones))
            }
        }

        //Actualizar Artista
        fun updateFileArtista(oldArtista: Artista, newArtista: Artista) {
            //toMutableList: para convertir una lista inmutable en mutable
            val artistas = readFromFileArtista().toMutableList()
            //indexOfFirst se utiliza para encontrar el índice del primer elemento en una colección que coincide con una condición específica.
            val index = artistas.indexOfFirst { it.nombreArtistico == oldArtista.nombreArtistico }
            if (index != -1) {
                artistas[index] = newArtista
                writeFileArtista(artistas)
            } else {
                println("Artista no encontrada.")
            }
        }

        //Borra el archivo por completo y lo vuelve a escribir
        fun writeFileArtista(artistas: List<Artista>) {
            val file = File(Artista.filePath)
            file.writeText("")
            artistas.forEach { writeToFileArtista(it) }
        }

        //Eliminar Artista
        fun deleteFromFileArtista(artista: Artista) {
            val artistas = readFromFileArtista().toMutableList()
            val index = artistas.indexOfFirst { it.nombreArtistico == artista.nombreArtistico }
            if (artistas.remove(artistas[index])) {
                writeFileArtista(artistas)
            } else {
                println("Artista no encontrada .")
            }
        }
        //Buscar Artista
        fun BuscarArtista(nombre: String):Artista?{
            val artistas = readFromFileArtista().toMutableList()
            val index = artistas.indexOfFirst { it.nombreArtistico == nombre  }
            return if (index != -1) {
                artistas[index]
            } else {
                null
            }
        }
    }
}