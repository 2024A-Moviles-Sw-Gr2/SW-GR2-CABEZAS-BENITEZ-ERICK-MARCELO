package com.example.appenmemoria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ArtistaCrear : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artista_crear)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_new_art)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        val artistaId = intent.getIntExtra("artista_id", -1)
        //
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_art)
        botonGuardar.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.txt_nombre_art).text.toString()
            val edad = findViewById<EditText>(R.id.txt_edad_art).text.toString().toIntOrNull()
            val latitud = findViewById<EditText>(R.id.txt_latitud_art).text.toString().toDoubleOrNull()
            val longitud = findViewById<EditText>(R.id.txt_longitud_art).text.toString().toDoubleOrNull()

            if (nombre.isNotEmpty() && edad != null && latitud!=null && longitud!=null) {
                if (artistaId == -1) {
                    // Crear un nuevo artista
                    val respuesta = EBaseDatos.tablas!!.crearArtista(
                        nombre,
                        edad,
                        latitud,
                        longitud
                    )
                    //val respuesta = BDatosMemoriaArt.crearArtista(nuevoArtista)
                    if (respuesta) {
                        irActividad(MainActivity::class.java)
                    } else {
                        mostrarSnackBar("Error al crear el Artista.")
                    }
                } else {
                    // Actualizar un artista existente
                    val respuesta = EBaseDatos.tablas!!.actualizarArtistaFormulario(nombre,edad,artistaId, latitud,longitud)
                    //val respuesta = BDatosMemoriaArt.actualizarArtista(artistaId, nuevoArtista)

                    if (respuesta) {
                        irActividad(MainActivity::class.java)
                    } else {
                        mostrarSnackBar("Error al actualizar el Artista.")
                    }
                }
            } else {
                mostrarSnackBar("Datos inválidos.")
            }
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
    fun mostrarSnackBar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_new_art),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

}