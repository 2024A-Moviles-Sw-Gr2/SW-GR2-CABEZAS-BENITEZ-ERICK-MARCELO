package org.example
import java.text.SimpleDateFormat

fun main() {
    /******************************CRUD CANCIONES*****************************************/
    //Creamos los archivos
    Cancion.createFileCancion(Cancion.filePath)
    Artista.createFileArtista(Artista.filePath)
    //Añadimos canciones
    println("Creamos canciones:")
    val formatoFecha = SimpleDateFormat("yyyy-MM-dd")

    val cancion1 = Cancion("Shape of You", formatoFecha.parse("2012-04-12"), true, 2012, 4.2)
    println(cancion1.nombre)
    val cancion2 = Cancion("jodido", formatoFecha.parse("2017-04-12"), false, 2017, 3.0)
    println(cancion2.nombre)

    // Escribir canciones en el archivo
    println("Ingresamos canciones al archivo:")
    Cancion.writeToFileCancion(cancion1)
    Cancion.writeToFileCancion(cancion2)

    // Leer canciones del archivo
    println("Leer canciones del archivo:")
    val canciones = Cancion.readFromFileCancion()
    canciones.forEach { println("Canción: ${it.nombre}") }

    // Actualizar una canción
    println("Actualizar cancion del archivo:")
    val cancionActualizada = Cancion("Shape of You final", formatoFecha.parse("2013-04-12"), true, 2013, 4.2)
    Cancion.updateFileCancion(cancion1, cancionActualizada)

    // Leer canciones del archivo después de la actualización
    val cancionesActualizadas = Cancion.readFromFileCancion()
    println("Canciones después de la actualización:")
    cancionesActualizadas.forEach { println("Canción: ${it.nombre}") }

    // Eliminar una canción
    Cancion.deleteFromFileCancion(cancionActualizada)

    //Añadir 2 canciones mas
    val cancion3 = Cancion("Dale tu muevelo", formatoFecha.parse("2020-04-20"), false, 2020, 4.2)
    println(cancion3.nombre)
    val cancion4 = Cancion("Perrea sola", formatoFecha.parse("2021-05-13"), true, 2021, 3.7)
    println(cancion4.nombre)

    // Escribir canciones en el archivo
    Cancion.writeToFileCancion(cancion3)
    Cancion.writeToFileCancion(cancion4)

    // Leer canciones del archivo después de la eliminación y agregar mas
    val cancionesFinales = Cancion.readFromFileCancion()
    println("Canciones después de la eliminación y agregar mas:")
    cancionesFinales.forEach { println("Canción: ${it.nombre}") }

    /*****************************CRUD ARTISTAS**********************************************/
    println("Ingresamos artistas")
    val cancionesMardix= arrayListOf(cancion2, cancion3)
    val artista1 = Artista("Mardix", formatoFecha.parse("2001-04-07"),true, 23, 1.79, cancionesMardix)
    println(artista1.nombreArtistico)
    val cancionesBenito= arrayListOf(cancion4)
    val artista2 = Artista("Benito", formatoFecha.parse("1995-04-12"), false, 29, 1.75, cancionesBenito)
    println(artista2.nombreArtistico)

    // Escribir artistas en el archivo
    println("Escribimos los artistas en el archivo")
    Artista.writeToFileArtista(artista1)
    Artista.writeToFileArtista(artista2)

    // Leer artistas del archivo
    val artistas = Artista.readFromFileArtista()
    println("Artistas actuales:")
    artistas.forEach { println("Artista: ${it.nombreArtistico}")}

    // Actualizar un artista
    val artistaActualizado = Artista("Bad bunny", formatoFecha.parse("1995-04-12"), false, 29, 1.75, cancionesBenito)
    Artista.updateFileArtista(artista2, artistaActualizado)

    // Leer canciones del archivo después de la actualización
    val artistasActualizados = Artista.readFromFileArtista()
    println("Artistas después de la actualización:")
    artistasActualizados.forEach { println("Artista: ${it.nombreArtistico}")}

    // Eliminar un artista
    Artista.deleteFromFileArtista(artista1)

    // Leer artistas del archivo después de la eliminacióñ
    val artistasFinal = Artista.readFromFileArtista()
    println("Artistas después de la eliminación:")
    artistasFinal.forEach { println("Artista: ${it.nombreArtistico}")}

    //agregar artista
    println("Agregar de nuevo al artista eliminado:")
    Artista.writeToFileArtista(artista1)
    println(artista1.nombreArtistico)

    val finalartistas = Artista.readFromFileArtista()
    println("Artistas después de agregar al artista eliminado:")
    finalartistas.forEach { println("Artista: ${it.nombreArtistico}")}
}
