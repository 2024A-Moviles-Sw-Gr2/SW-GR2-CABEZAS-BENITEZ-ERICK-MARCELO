package org.example

import com.google.gson.Gson
import java.io.File
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
        val gson = Gson()

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
            /*val file = File(filePath)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")*/
            //agregar texto sin subrescribir lo anterior
            /*file.appendText("${cancion.nombre},${dateFormat.format(cancion.fechaDeLanzamiento)},${cancion.popular},${cancion.anio},${cancion.duracion}\n")
            println("canción ${cancion.nombre} ingresadas al archivo")*/
            if(buscarCancion(cancion.nombre)!=null){
                println("canción ${cancion.nombre} ya ingresada")
            }else{
                val file = File(filePath)
                val jsonString = gson.toJson(cancion)
                // Agregar el objeto al final del archivo de texto
                file.appendText(jsonString + "\n")
                println("Canción ${cancion.nombre} ingresada")
            }
        }
        //Leer cancion
        fun readFromFileCancion(): List<Cancion> {
            val file = File(filePath)

            // Leer el contenido del archivo de texto
            val jsonStringFromFile = file.readText()

            // Dividir el contenido del archivo en líneas y deserializar cada línea
            val cancionesFromFile = jsonStringFromFile.lines().filter { it.isNotBlank() }.map {
                gson.fromJson(it, Cancion::class.java)
            }

            return cancionesFromFile

            /*if (!file.exists()) {
                return emptyList()
            }
            return file.readLines().map {
                val parts = it.split(",")
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                Cancion(parts[0],dateFormat.parse(parts[1]),parts[2].toBoolean(),parts[3].toInt(),parts[4].toDouble())
            }*/
        }

        //Actualizar Cancion
        fun updateFileCancion(nombre: String, newCancion: Cancion) {
            //toMutableList: para convertir una lista inmutable en mutable
            val canciones = readFromFileCancion().toMutableList()
            //indexOfFirst se utiliza para encontrar el índice del primer elemento en una colección que coincide con una condición específica.
            val index = buscarCancion(nombre)
            if (index != null) {
                canciones[index] = newCancion
                writeFileCancion(canciones)
                println("Canción ${nombre} actualizada a ${newCancion.nombre}")
            } else {
                println("Canción ${nombre} no encontrada para actualizar.")
            }
        }

        //borra el archivo por completo y lo vuelve a escribir
        fun writeFileCancion(canciones: List<Cancion>) {
            val file = File(filePath)
            file.writeText("")
            canciones.forEach { writeToFileCancion(it) }
        }
        //Eliminar canción
        fun deleteFromFileCancion(nombre: String) {
            val canciones = readFromFileCancion().toMutableList()
            val index = buscarCancion(nombre)
            if (index!=null) {
                canciones.remove(canciones[index])
                writeFileCancion(canciones)
                println("Canción  ${nombre} eliminada.")
            } else {
                println("Canción  ${nombre} no encontrada para eliminarla.")
            }
        }
        //BuscarCancion
        fun buscarCancion(nombre: String): Int?{
            val canciones = readFromFileCancion().toMutableList()
            val index = canciones.indexOfFirst { it.nombre.equals(nombre, ignoreCase = true)  }
            return if (index != -1) {
                index
            } else {
                null
            }
        }

        fun CancionPorNombre(nombre: String): Cancion?{
            val canciones = readFromFileCancion().toMutableList()
            val index = canciones.indexOfFirst { it.nombre.equals(nombre, ignoreCase = true)  }
            return if (index != -1) {
                canciones[index]
            } else {
                null
            }
        }

    }
}