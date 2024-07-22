package com.example.crudentidad

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteArtista(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
){
    override fun onCreate (db: SQLiteDatabase?){
        val scriptQLCrearTablaArtista =
            """
                CREATE TABLE ARTISTA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    canciones VARCHAR(50),
                    nacimiento VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptQLCrearTablaArtista)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2:Int){}

    fun crearArtista(
        nombre:String,
        canciones:String,
        nacimiento:String
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("canciones", canciones)
        valoresAGuardar.put("nacimiento", nacimiento)
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
        nombre:String, canciones:String, nacimiento:String, id:Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar= ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("canciones", canciones)
        valoresAActualizar.put("nacimiento", nacimiento)
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
        //Logica de busqueda
        //Recibimos un arreglo (puede ser nulo)
        //llenar un areglo de entrenadores
        //Si esta vacio, el arreglo no tiene elementos

        val existeALMenosUno= resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BArtista>()
        if(existeALMenosUno){
            do{
                val artista = BArtista(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getString(3)
                )
                arregloRespuesta.add(artista)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        // ESqLiteHelperEntrenador.consultarEntrenadorPorID
        return  if(arregloRespuesta.size>0) arregloRespuesta[0] else null
    }

    fun consultarArtistasBasico(): ArrayList<BArtista> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT id, nombre, nacimiento FROM ARTISTA
    """.trimIndent()

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val listaArtistas = ArrayList<BArtista>()
        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val artista = BArtista(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    "", // Dejamos el campo 'canciones' vacío
                    resultadoConsultaLectura.getString(2)
                )
                listaArtistas.add(artista)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return listaArtistas
    }
}