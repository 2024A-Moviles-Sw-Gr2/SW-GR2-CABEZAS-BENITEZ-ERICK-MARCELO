package org.example
import java.text.SimpleDateFormat

fun main() {
    var opc=0

    do {
        // Menu
        println("\n Ingrese la opción deseada: \n" +
                "1) Gestionar canciones\n" +
                "2) Gestionar artistas\n" +
                "3) Salir")
        opc = readLine()?.toIntOrNull() ?: 0

        if(opc==1){
            do{
                println("\n Ingrese la opción deseada: \n" +
                        "1) Ingresar una canción\n" +
                        "2) Actualizar una canción\n" +
                        "3) Eliminar una canción\n" +
                        "4) Mirar canciones registradas\n"+
                        "5) Salir\n")
                opc = readLine()?.toIntOrNull() ?: 0

                when (opc) {
                    1 -> {
                        println("Ingrese el nombre: ")
                        val nombre = readLine() ?: ""
                        println("Ingrese la fecha de creación en el formato mostrado. Ejem: 2012-04-12")
                        val fecha = readLine() ?: ""
                        println("La canción es popular? responda 1 si es Verdad y 0 si es Falso")
                        val popularOpc = readLine()?.toIntOrNull() ?: 0
                        var popular = false
                        when (popularOpc){
                            0 -> popular=false
                            1 -> popular=true
                        }
                        println("Ingrese el año publicación: ")
                        val anio = readLine()?.toIntOrNull() ?: 0

                        println("Ingrese la duración de la canción: ")
                        val duracion = readLine()?.toDoubleOrNull() ?: 0.0

                        val formatoFecha = SimpleDateFormat("yyyy-MM-dd")

                        val cancion = Cancion(nombre, formatoFecha.parse(fecha), popular, anio, duracion)
                        Cancion.writeToFileCancion(cancion)
                    }
                    2 -> {
                        println("Ingrese el nombre de la canción que va ser actualizada: ")
                        val nombreAntiguo = readLine() ?: ""

                        println("*****Datos para actualizar canción*****")

                        println("Ingrese el nombre actualizado: ")
                        val nombre = readLine() ?: ""

                        println("Ingrese la fecha de creación en el formato mostrado. Ejem: 2012-04-12")
                        val fecha = readLine() ?: ""

                        println("La canción es popular Actualmente? responda 1 si es Verdad y 0 si es Falso")
                        val popularOpc = readLine()?.toIntOrNull() ?: 0

                        var popular = false
                        when (popularOpc){
                            0 -> popular=false
                            1 -> popular=true
                        }

                        println("Ingrese el año publicación: ")
                        val anio = readLine()?.toIntOrNull() ?: 0

                        println("Ingrese la duración de la canción: ")
                        val duracion = readLine()?.toDoubleOrNull() ?: 0.0

                        val formatoFecha = SimpleDateFormat("yyyy-MM-dd")

                        val cancion = Cancion(nombre, formatoFecha.parse(fecha), popular, anio, duracion)

                        Cancion.updateFileCancion(nombreAntiguo, cancion)
                    }
                    3 -> {
                        println("Ingrese el nombre de la canción ha eliminar: ")
                        val nombre = readLine() ?: ""
                        Cancion.deleteFromFileCancion(nombre)
                    }
                    4 -> {
                        println("las canciones actuales son las siguientes")

                        val canciones = Cancion.readFromFileCancion()

                        canciones.forEach { println(it.nombre) }
                    }
                    5 -> opc=5
                    else -> println("la opción ingresada es incorrecta")
                }
            }while (opc!=5)

        }else if (opc==2){
            do{
                print("\n Ingrese la opción deseada: \n" +
                        "1) Ingresar un artista\n" +
                        "2) Actualizar artista\n" +
                        "3) Eliminar artista\n" +
                        "4) Mirar artistas registrados\n"+
                        "5) Salir\n")
                opc = readLine()?.toIntOrNull() ?: 0

                when (opc) {
                    1 -> {
                        println("Ingrese el nombre del artista: ")
                        val nombre = readLine() ?: ""
                        println("Ingrese la fecha de nacimiento del artista en el formato mostrado. Ejem: 2012-04-12")
                        val fecha = readLine() ?: ""
                        println("El artista esta retirado? responda 1 si es Verdad y 0 si es Falso")
                        val retiradoOpc = readLine()?.toIntOrNull() ?: 0
                        var retirado = false
                        when (retiradoOpc){
                            0 -> retirado=false
                            1 -> retirado=true
                        }
                        println("Ingrese la edad del artista: ")
                        val edad = readLine()?.toIntOrNull() ?: 0

                        println("Ingrese la estatura del artista: ")
                        val estatura = readLine()?.toDoubleOrNull() ?: 0.0

                        println("Ingrese el nombre de las caniones del artista separadas por una coma. Ejem: mata,llorona,etc....")
                        val canciones = readLine() ?: ""
                        val listacanciones = canciones.split(",")
                        val lista: ArrayList<Cancion> = ArrayList()

                        listacanciones.forEach {
                            val song = Cancion.CancionPorNombre(it)
                            if (song != null) {
                                lista.add(song)
                            }
                        }

                        val formatoFecha = SimpleDateFormat("yyyy-MM-dd")

                        val artista = Artista(nombre, formatoFecha.parse(fecha),retirado, edad, estatura , lista)
                        Artista.writeToFileArtista(artista)

                    }
                    2 -> {

                        println("Ingrese el nombre del artista que sera actualizado: ")
                        val nombreActualizar = readLine() ?: ""

                        println("****Actualización de artista****")
                        println("Ingrese el nombre del artista que sera actualizado: ")
                        val nombre = readLine() ?: ""

                        println("Ingrese la fecha de nacimiento del artista en el formato mostrado. Ejem: 2012-04-12")
                        val fecha = readLine() ?: ""
                        println("El artista esta retirado? responda 1 si es Si y 0 si es No")
                        val retiradoOpc = readLine()?.toIntOrNull() ?: 0
                        var retirado = false
                        when (retiradoOpc){
                            0 -> retirado=false
                            1 -> retirado=true
                        }
                        println("Ingrese la edad del artista: ")
                        val edad = readLine()?.toIntOrNull() ?: 0

                        println("Ingrese la estatura del artista: ")
                        val estatura = readLine()?.toDoubleOrNull() ?: 0.0

                        println("Ingrese el nombre de las canciones del artista separadas por una coma. Ejem: mata,llorona,etc....")
                        val canciones = readLine() ?: ""
                        val listacanciones = canciones.split(",")
                        val lista: ArrayList<Cancion> = ArrayList()

                        listacanciones.forEach {
                            val song = Cancion.CancionPorNombre(it)
                            if (song != null) {
                                lista.add(song)
                            }
                        }

                        val formatoFecha = SimpleDateFormat("yyyy-MM-dd")

                        val artista = Artista(nombre, formatoFecha.parse(fecha),retirado, edad, estatura , lista)
                        Artista.updateFileArtista(nombreActualizar, artista)

                    }
                    3 -> {
                        println("Ingrese el nombre del artista que sera eliminado: ")
                        val nombreEliminar = readLine() ?: ""

                        Artista.deleteFromFileArtista(nombreEliminar)
                    }
                    4 -> {
                        println("Los artistas guardados son los siguientes")
                        val artistas=Artista.readFromFileArtista()

                        artistas.forEach { println(it.nombreArtistico) }

                    }
                    5 -> opc=5
                    else -> println("la opción ingresada es incorrecta")
                }
            }while (opc!=5)

        }else{
            println("ingrese una opción correcta")
        }

    } while (opc!=3)
    /*
    /******************************EJEMPLO CRUD CANCIONES*****************************************/
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
    Cancion.updateFileCancion(cancion1.nombre, cancionActualizada)

    // Leer canciones del archivo después de la actualización
    val cancionesActualizadas = Cancion.readFromFileCancion()
    println("Canciones después de la actualización:")
    cancionesActualizadas.forEach { println("Canción: ${it.nombre}") }

    // Eliminar una canción
    Cancion.deleteFromFileCancion(cancionActualizada.nombre)

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
    Artista.updateFileArtista(artista2.nombreArtistico, artistaActualizado)

    // Leer artistas del archivo después de la actualización
    val artistasActualizados = Artista.readFromFileArtista()
    println("Artistas después de la actualización:")
    artistasActualizados.forEach { println("Artista: ${it.nombreArtistico}")}

    // Eliminar un artista
    Artista.deleteFromFileArtista(artista1.nombreArtistico)

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
    finalartistas.forEach { println("Artista: ${it.nombreArtistico}")}*/
}
