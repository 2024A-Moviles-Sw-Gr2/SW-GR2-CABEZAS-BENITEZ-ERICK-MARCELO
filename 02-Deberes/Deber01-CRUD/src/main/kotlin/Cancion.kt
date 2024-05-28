package org.example

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class Cancion (
    val nombre: String,
    val fechaDeLanzamiento: Date,
    var popular: Boolean,
    var anio: Int,
    val duracion: Double
) {
    init {

    }
    companion object {
        val filePath = "src/main/resources/canciones.txt"

        fun createFileCancion(filePath: String) {
            val file = File(filePath)
            if (file.createNewFile()) {
                println("Archivo creado: ${file.name}")
            } else {
                println("Archivo existente.")
            }
        }

        //Escribir canción
        fun writeToFileCancion(cancion: Cancion) {
            val file = File(Cancion.filePath)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            //agregar texto sin subrescribir lo anterior
            file.appendText("${cancion.nombre},${dateFormat.format(cancion.fechaDeLanzamiento)},${cancion.popular},${cancion.anio},${cancion.duracion}\n")
            println("canción ${cancion.nombre} ingresadas al archivo")
        }
        //Leer cancion
        fun readFromFileCancion(): List<Cancion> {
            val file = File(Cancion.filePath)
            if (!file.exists()) {
                return emptyList()
            }
            return file.readLines().map {
                val parts = it.split(",")
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                Cancion(parts[0],dateFormat.parse(parts[1]),parts[2].toBoolean(),parts[3].toInt(),parts[4].toDouble())
            }
        }
        //Actualizar Cancion
        fun updateFileCancion(oldCancion: Cancion, newCancion: Cancion) {
            //toMutableList: para convertir una lista inmutable en mutable
            val canciones = readFromFileCancion().toMutableList()
            //indexOfFirst se utiliza para encontrar el índice del primer elemento en una colección que coincide con una condición específica.
            val index = canciones.indexOfFirst { it.nombre == oldCancion.nombre }
            if (index != -1) {
                canciones[index] = newCancion
                writeFileCancion(canciones)
            } else {
                println("Canción no encontrada.")
            }
        }

        //borra el archivo por completo y lo vuelve a escribir
        fun writeFileCancion(canciones: List<Cancion>) {
            val file = File(Cancion.filePath)
            file.writeText("")
            canciones.forEach { writeToFileCancion(it) }
        }
        //Eliminar canción
        fun deleteFromFileCancion(cancion: Cancion) {
            val canciones = readFromFileCancion().toMutableList()
            val index = canciones.indexOfFirst { it.nombre == cancion.nombre }
            if (canciones.remove(canciones[index])) {
                writeFileCancion(canciones)
            } else {
                println("Canción no encontrada .")
            }
        }
        //BuscarCancion
        fun BuscarCancion(nombre: String):Cancion?{
            val canciones = readFromFileCancion().toMutableList()
            val index = canciones.indexOfFirst { it.nombre ==nombre  }
            return if (index != -1) {
                canciones[index]
            } else {
                null
            }
        }

    }
}