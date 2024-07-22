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

class CancionCrear : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cancion_crear)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_crear_cancion)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //
        val cancionId = intent.getIntExtra("cancion_id", -1)
        val artistaId = intent.getIntExtra("artista_id", -1)
        //
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_cancion)
        botonGuardar.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.txt_nombreCancion).text.toString()
            val tipo = findViewById<EditText>(R.id.txt_tipoCancion).text.toString()
            val anio = findViewById<EditText>(R.id.txt_anioCancion).text.toString().toIntOrNull()

            if (nombre.isNotEmpty() && tipo.isNotEmpty() && anio!=null) {
                if (cancionId == -1) {
                    // Crear un nueva cancion
                    val id = BDatosMemoriaSong.idsSong+1
                    val nuevaCancion = BCancion(id, nombre, tipo, anio,artistaId)
                    val respuesta = BDatosMemoriaSong.crearCancion(nuevaCancion)

                    if (respuesta) {
                        BDatosMemoriaSong.idsSong+=1
                        irActividad(Canciones::class.java, artistaId)
                    } else {
                        mostrarSnackBar("Error al crear la canción.")
                    }
                } else {
                    // Actualizar un canción existente
                    val nuevaCancion = BCancion(cancionId, nombre, tipo, anio,artistaId)
                    val respuesta = BDatosMemoriaSong.actualizarCancion(cancionId,nuevaCancion)

                    if (respuesta) {
                        irActividad(Canciones::class.java, artistaId)
                    } else {
                        mostrarSnackBar("Error al actualizar canción.")
                    }
                }
            } else {
                mostrarSnackBar("Datos inválidos.")
            }
        }
    }
    fun irActividad(
        clase: Class<*>,
        artistaId: Int?
    ){
        val intent = Intent(this, clase)
        intent.putExtra("artista_id", artistaId)
        startActivity(intent)
    }
    fun mostrarSnackBar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_crear_cancion),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}