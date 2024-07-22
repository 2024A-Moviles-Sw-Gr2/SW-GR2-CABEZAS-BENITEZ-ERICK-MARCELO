package com.example.crudentidad

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteCancion(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
){
    override fun onCreate (db: SQLiteDatabase?){
        val scriptQLCrearTablaCancion =
            """
            CREATE TABLE CANCION(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                tipo VARCHAR(50),
                anio INTEGER,
                artista_id INTEGER,
                FOREIGN KEY (artista_id) REFERENCES ARTISTA(id)
            )
        """.trimIndent()
        db?.execSQL(scriptQLCrearTablaCancion)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2:Int){}

    fun crearCancion(
        nombre:String,
        tipo:String,
        anio:Int,
        artistaId: Int // Nuevo parámetro para la clave foránea
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("tipo", tipo)
        valoresAGuardar.put("anio", anio)
        valoresAGuardar.put("artista_id", artistaId) // Añadir el artista_id a los valores

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
        nombre:String, tipo: String, anio: Int, id:Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar= ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("tipo", tipo)
        valoresAActualizar.put("anio", anio)
        //where: ....
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "CANCION",
                valoresAActualizar,
                "id=?",// id=1
                parametrosConsultaActualizar //[1]
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarCANCIONPorID(id:Int):BCancion?{
        val baseDatosLectura= readableDatabase
        val scriptConsultaLectura="""
            SELECT * FROM CANCION WHERE ID = ?
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
        val arregloRespuesta = arrayListOf<BCancion>()
        if(existeALMenosUno){
            do{
                val cancion = BCancion(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getInt(3)
                )
                arregloRespuesta.add(cancion)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        // ESqLiteHelperEntrenador.consultarEntrenadorPorID
        return  if(arregloRespuesta.size>0) arregloRespuesta[0] else null
    }
    fun consultarCancionesPorArtistaId(artistaId: Int): List<BCancion> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM CANCION WHERE artista_id = ?
    """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(
            artistaId.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )

        val canciones = mutableListOf<BCancion>()
        val existeALMenosUna = resultadoConsultaLectura.moveToFirst()
        if (existeALMenosUna) {
            do {
                val cancion = BCancion(
                    resultadoConsultaLectura.getInt(0), // id
                    resultadoConsultaLectura.getString(1), // nombre
                    resultadoConsultaLectura.getString(2), // tipo
                    resultadoConsultaLectura.getInt(3) // anio
                )
                canciones.add(cancion)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return canciones
    }
}