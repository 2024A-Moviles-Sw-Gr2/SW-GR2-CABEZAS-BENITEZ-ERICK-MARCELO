package org.example

import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun main() {
    //Creamos los archivos
    CRUD.createFile(CRUD.filePath)
    CRUD.createFile(CRUD.filePathArtista)
    //Añadimos canciones
    val formatoFecha = SimpleDateFormat("yyyy-MM-dd")

    val cancion1 = Cancion("Shape of You", formatoFecha.parse("2012-04-12"), true, 2012, 4.2)
    val cancion2 = Cancion("jodido", formatoFecha.parse("2017-04-12"), false, 2017, 3.0)

    // Escribir canciones en el archivo
    CRUD.writeToFileCancion(cancion1)
    CRUD.writeToFileCancion(cancion2)

    // Leer canciones del archivo
    val canciones = CRUD.readFromFileCancion()
    canciones.forEach { println("Canción: ${it.nombre}") }

    // Actualizar una canción
    val cancionActualizada = Cancion("Shape of You", formatoFecha.parse("2013-04-12"), true, 2013, 4.2)
    CRUD.updateFileCancion(cancion1, cancionActualizada)

    // Leer canciones del archivo después de la actualización
    val cancionesActualizadas = CRUD.readFromFileCancion()
    println("Canciones después de la actualización:")
    cancionesActualizadas.forEach { println("Canción: ${it.nombre}") }

    // Eliminar una canción
    CRUD.deleteFromFileCancion(cancionActualizada)

    //Añadir 2 canciones mas
    val cancion3 = Cancion("Dale tu muevelo", formatoFecha.parse("2020-04-20"), false, 2020, 4.2)
    val cancion4 = Cancion("Perrea sola", formatoFecha.parse("2021-05-13"), true, 2021, 3.7)

    // Escribir canciones en el archivo
    CRUD.writeToFileCancion(cancion3)
    CRUD.writeToFileCancion(cancion4)

    // Leer canciones del archivo después de la eliminación y agregar mas
    val cancionesFinales = CRUD.readFromFileCancion()
    println("Canciones después de la eliminación:")
    cancionesFinales.forEach { println("Canción: ${it.nombre}") }

    // guardar un Artista
    val cancionesMardix= arrayListOf(cancion2, cancion3)
    val artista1 = Artista("Mardix", formatoFecha.parse("2001-04-07"),true, 23, 1.79, cancionesMardix)

    val cancionesBenito= arrayListOf(cancion4)
    val artista2 = Artista("Benito", formatoFecha.parse("1995-04-12"), false, 29, 1.75, cancionesBenito)

    // Escribir artistas en el archivo
    CRUD.writeToFileArtista(artista1)
    CRUD.writeToFileArtista(artista2)

    // Leer artistas del archivo
    val artistas = CRUD.readFromFileArtista()
    println("Artistas actuales:")
    artistas.forEach { println("Artista: ${it.nombreArtistico}")}

    // Actualizar una canción
    val artistaActualizado = Artista("Bad bunny", formatoFecha.parse("1995-04-12"), false, 29, 1.75, cancionesBenito)
    CRUD.updateFileArtista(artista2, artistaActualizado)

    // Leer canciones del archivo después de la actualización
    val artistasActualizados = CRUD.readFromFileArtista()
    println("Artistas después de la actualización:")
    artistasActualizados.forEach { println("Artista: ${it.nombreArtistico}")}

    // Eliminar un artista
    CRUD.deleteFromFileArtista(artista1)

    // Leer canciones del archivo después de la actualización
    val artistasFinal = CRUD.readFromFileArtista()
    println("Artistas después de la eliminación:")
    artistasFinal.forEach { println("Artista: ${it.nombreArtistico}")}

    //agregar artista
    CRUD.writeToFileArtista(artista1)

    val finalartistas = CRUD.readFromFileArtista()
    println("Artistas después de agregar al mardix:")
    finalartistas.forEach { println("Artista: ${it.nombreArtistico}")}

}

class CRUD(){
    init {

    }
    companion object {
        //canciones

        val filePath = "src/main/resources/canciones.txt"
        val filePathArtista = "src/main/resources/artista.txt"

        ////CRUD CANCIONES
        //Crear el archivo
        fun createFile(filePath: String) {
            val file = File(filePath)
            if (file.createNewFile()) {
                println("Archivo creado: ${file.name}")
            } else {
                println("Archivo existente.")
            }
        }

        //Escribir canción
        fun writeToFileCancion(cancion: Cancion) {
            val file = File(filePath)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            //agregar texto sin subrescribir lo anterior
            file.appendText("${cancion.nombre},${dateFormat.format(cancion.fechaDeLanzamiento)},${cancion.popular},${cancion.anio},${cancion.duracion}\n")
            println("canción ingresadas al archivo")
        }
        //Leer cancion
        fun readFromFileCancion(): List<Cancion> {
            val file = File(filePath)
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
            val file = File(filePath)
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


        ///CRUD ARTISTAS

        //Escribir Artista
        fun writeToFileArtista(artista: Artista) {
            val file = File(filePathArtista)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val cancionesString = artista.canciones.map { it.nombre }.joinToString(separator = ";")
            file.appendText("${artista.nombreArtistico},${dateFormat.format(artista.fechaDeNacimiento)},${artista.retirado},${artista.edad},${artista.estatura},$cancionesString\n")
        }

        //Leer Artista
        fun readFromFileArtista(): List<Artista> {
            val file = File(filePathArtista)
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
                val canciones = parts[5].split(";").map { BuscarCancion(it) }
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
            val file = File(filePathArtista)
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

// funciones del CRUD


////
class Artista(
    val nombreArtistico: String,
    val fechaDeNacimiento: Date,
    var retirado: Boolean,
    var edad: Int,
    val estatura: Double,
    var canciones: ArrayList<Cancion>

){
    init {
        println("Se ha creado el artista $nombreArtistico")
    }
    companion object {

    }
}

class Cancion(
    val nombre: String,
    val fechaDeLanzamiento: Date,
    var popular: Boolean,
    var anio: Int,
    val duracion: Double

){
    init {
        println("Se ha creado la canción $nombre")
    }
    companion object {

    }
}
