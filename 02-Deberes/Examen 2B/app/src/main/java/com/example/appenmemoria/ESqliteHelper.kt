package com.example.appenmemoria

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelper(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "AdroidSQlite",
    null,
    1
){
    val arregloRespuestaArtista = arrayListOf<BArtista>()
    val arregloRespuestaCancion = arrayListOf<BCancion>()
    override fun onCreate (db: SQLiteDatabase?){
        val scriptQLCrearTablaArtista =
            """
                CREATE TABLE ARTISTA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    edad INTEGER,
                    latitud DOUBLE,
                    longitud DOUBLE
                );
            """.trimIndent()
        db?.execSQL(scriptQLCrearTablaArtista)
        val scriptQLCrearTablaCancion =
            """
                CREATE TABLE CANCION(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    tipo VARCHAR(50),
                    anio INTEGER,
                    artistaId INTEGER
                );
            """.trimIndent()
        db?.execSQL(scriptQLCrearTablaCancion)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2:Int){}

    fun crearArtista(
        nombre:String,
        edad:Int,
        latitud: Double,
        longitud: Double
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("edad", edad)
        valoresAGuardar.put("latitud", latitud)
        valoresAGuardar.put("longitud", longitud)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "ARTISTA",// nomre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt()==-1) false else true
    }
    fun eliminarArtistaFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        // Consulta SQL: where .... ID=? AND NOMBRE=? [?=1,?=2]
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ARTISTA",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarArtistaFormulario(
        nombre:String, edad:Int, id:Int, latitud: Double,
        longitud: Double
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar= ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("edad", edad)
        valoresAActualizar.put("latitud", latitud)
        valoresAActualizar.put("longitud", longitud)
        //where: ....
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "ARTISTA",
                valoresAActualizar,//nombre= Adrian, descripción
                "id=?",// id=1
                parametrosConsultaActualizar //[1]
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarArtistas(): List<BArtista>{
        val baseDatosLectura= readableDatabase
        val scriptConsultaLectura="""
            SELECT * FROM ARTISTA
        """.trimIndent()
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                null
            )

        val existeALMenosUno= resultadoConsultaLectura
            .moveToFirst()
        if(existeALMenosUno){
            do{
                val artista = BArtista(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getInt(2),
                    resultadoConsultaLectura.getDouble(3),
                    resultadoConsultaLectura.getDouble(4)
                )
                arregloRespuestaArtista.add(artista)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        // ESqLiteHelperEntrenador.consultarEntrenadorPorID
        return  arregloRespuestaArtista
    }

    fun consultarArtistaPorID(id:Int):BArtista?{
        val baseDatosLectura= readableDatabase
        val scriptConsultaLectura="""
            SELECT * FROM ARTISTA WHERE ID = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura= arrayOf(
            id.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )
        val existeALMenosUno= resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BArtista>()
        if(existeALMenosUno){
            do{
                val artista = BArtista(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getInt(2),
                    resultadoConsultaLectura.getDouble(3),
                    resultadoConsultaLectura.getDouble(4)
                )
                arregloRespuesta.add(artista)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        // ESqLiteHelperEntrenador.consultarEntrenadorPorID
        return  if(arregloRespuesta.size>0) arregloRespuesta[0] else null
    }
    /*************************************Canciones*****************************************************/

    fun crearCancion(
        nombre:String,
        tipo:String,
        anio:Int,
        artistaId:Int
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("tipo", tipo)
        valoresAGuardar.put("anio", anio)
        valoresAGuardar.put("artistaId", artistaId)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "CANCION",// nomre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt()==-1) false else true
    }
    fun eliminarCancionFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        // Consulta SQL: where .... ID=? AND NOMBRE=? [?=1,?=2]
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "CANCION",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarCancionFormulario(
        nombre:String, tipo:String, anio:Int, artistaId: Int, id: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar= ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("tipo", tipo)
        valoresAActualizar.put("anio", anio)
        valoresAActualizar.put("artistaId", artistaId)
        //where: ....
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "CANCION",
                valoresAActualizar,//nombre= Adrian, descripción
                "id=?",// id=1
                parametrosConsultaActualizar //[1]
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt()==-1) false else true
    }
    fun consultarCancionPorIDArtista(artistaId:Int):List<BCancion>{
        val baseDatosLectura= readableDatabase
        val scriptConsultaLectura="""
            SELECT * FROM CANCION WHERE artistaId = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura= arrayOf(
            artistaId.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )
        //Logica de busqueda
        //Recibimos un arreglo (puede ser nulo)
        //llenar un areglo de entrenadores
        //Si esta vacio, el arreglo no tiene elementos

        val existeALMenosUno= resultadoConsultaLectura
            .moveToFirst()
        if(existeALMenosUno){
            do{
                val cancion = BCancion(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getInt(3),
                    resultadoConsultaLectura.getInt(4),
                )
                arregloRespuestaCancion.add(cancion)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        // ESqLiteHelperEntrenador.consultarEntrenadorPorID
        return  arregloRespuestaCancion
    }


}

