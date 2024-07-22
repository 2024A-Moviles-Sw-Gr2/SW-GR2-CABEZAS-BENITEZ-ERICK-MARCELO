package com.example.crudentidad

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class CancionesCRUD : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var cancionDatabaseHelper: ESqliteCancion
    private lateinit var cancionAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_esqlite_canciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_crud_canciones)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //-------
        listView = findViewById(R.id.lv_lisrt_view)
        //-------
        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_cancion)
        //--
        val artistaId = intent.getIntExtra("ARTISTA_ID", -1)
        if (artistaId != -1) {
            cancionDatabaseHelper = ESqliteCancion(this)
            val canciones = cancionDatabaseHelper.consultarCancionesPorArtistaId(artistaId)

            val nombresCanciones = canciones.map { it.nombre }
            cancionAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, nombresCanciones)
            listView.adapter = cancionAdapter
        }
        //--
        botonCrearBDD.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.txt_nombre_cancion)
            val tipo = findViewById<EditText>(R.id.txt_tipo_cancion)
            val anio = findViewById<EditText>(R.id.txt_anio_cancion)
            val respuesta = CancionBaseDatos.tablaCancion !!
                .crearCancion(
                    nombre.text.toString(),
                    tipo.text.toString(),
                    anio.text.toString().toInt(),
                    artistaId
                )
            if(respuesta) mostrarSnackBar("Song. añadida!")
        }
        val botonActualizarBDD = findViewById<Button>(R.id.btn_editar_cancion)
        botonActualizarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.txt_id_cancion)
            val nombre = findViewById<EditText>(R.id.txt_nombre_cancion)
            val tipo = findViewById<EditText>(R.id.txt_tipo_cancion)
            val anio = findViewById<EditText>(R.id.txt_anio_cancion)
            val respuesta = CancionBaseDatos.tablaCancion !!
                .actualizarCancionFormulario(
                    nombre.text.toString(),
                    tipo.text.toString(),
                    anio.text.toString().toInt(),
                    id.text.toString().toInt()
                )
            if(respuesta) mostrarSnackBar("Song. actualizada!")
        }
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_cancion)
        botonEliminarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.txt_id_cancion)
            val respuesta = CancionBaseDatos.tablaCancion!!
                .eliminarCancionFormulario(
                    id.text.toString().toInt()
                )
            if(respuesta) mostrarSnackBar("Song. Eliminada")
        }
    }
    fun mostrarSnackBar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_crud_canciones),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}