package org.example

import com.google.gson.Gson
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
        val gson = Gson()
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
            /*val file = File(Artista.filePath)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val cancionesString = artista.canciones.map { it.nombre }.joinToString(separator = ";")
            file.appendText("${artista.nombreArtistico},${dateFormat.format(artista.fechaDeNacimiento)},${artista.retirado},${artista.edad},${artista.estatura},$cancionesString\n")
            println("Artista ${artista.nombreArtistico} ingresadas al archivo")*/
            if(buscarArtista(artista.nombreArtistico)!=null){
                println("Artista ${artista.nombreArtistico} ya ingresado.")
            }else{
                val file = File(filePath)
                val jsonString = gson.toJson(artista)
                // Agregar el objeto al final del archivo de texto
                file.appendText(jsonString + "\n")
                println("Artista ${artista.nombreArtistico} ingresado.")
            }
        }

        //Leer Artista
        fun readFromFileArtista(): List<Artista> {
            /*val file = File(Artista.filePath)
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
                val canciones = parts[5].split(";").map { Cancion.buscarCancion(it) }
                Artista(nombreArtistico, fechaDeNacimiento, retirado, edad, estatura, ArrayList(canciones))
            }*/
            val file = File(filePath)

            // Leer el contenido del archivo de texto
            val jsonStringFromFile = file.readText()

            // Dividir el contenido del archivo en líneas y deserializar cada línea
            val artistasFromFile = jsonStringFromFile.lines().filter { it.isNotBlank() }.map {
                gson.fromJson(it, Artista::class.java)
            }

            return artistasFromFile
        }

        //Actualizar Artista
        fun updateFileArtista(nombre: String, newArtista: Artista) {
            //toMutableList: para convertir una lista inmutable en mutable
            val artistas = readFromFileArtista().toMutableList()
            //indexOfFirst se utiliza para encontrar el índice del primer elemento en una colección que coincide con una condición específica.
            val index = buscarArtista(nombre)
            if (index != null) {
                artistas[index] = newArtista
                writeFileArtista(artistas)
                println("Artista ${nombre} actualizado ha ${newArtista.nombreArtistico}.")
            } else {
                println("Artista ${nombre} no encontrado para la actualización.")
            }
        }

        //Borra el archivo por completo y lo vuelve a escribir
        fun writeFileArtista(artistas: List<Artista>) {
            val file = File(filePath)
            file.writeText("")
            artistas.forEach { writeToFileArtista(it) }
        }

        //Eliminar Artista
        fun deleteFromFileArtista(nombre: String) {
            val artistas = readFromFileArtista().toMutableList()
            val index = buscarArtista(nombre)
            if (index!=null) {
                artistas.remove(artistas[index])
                writeFileArtista(artistas)
                println("Artista ${nombre} eliminado.")
            } else {
                println("Artista ${nombre} no encontrada para eliminarlo.")
            }
        }
        //Buscar Artista
        fun buscarArtista(nombre: String): Int?{
            val artistas = readFromFileArtista().toMutableList()
            val index = artistas.indexOfFirst { it.nombreArtistico.equals(nombre, ignoreCase = true) }
            return if (index != -1) {
                index
            } else {
                null
            }
        }
    }
}