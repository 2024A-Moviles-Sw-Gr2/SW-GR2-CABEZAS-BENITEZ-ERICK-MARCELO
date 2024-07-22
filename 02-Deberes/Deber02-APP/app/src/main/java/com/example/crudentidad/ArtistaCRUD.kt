package com.example.crudentidad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ArtistaCRUD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artista_crud)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_crud_artista)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // buscar entrebnador
        val botonBuscarBDD = findViewById<Button>(R.id.btn_crud_buscar)
        botonBuscarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.txt_crud_id)
            val nombre = findViewById<EditText>(R.id.txt_crud_nombre)
            val canciones = findViewById<EditText>(R.id.txt_crud_canciones)
            val nacimiento = findViewById<EditText>(R.id.txt_fechaDeNacimiento)

            val artista = ArtistaBaseDatos.tablaArtista !!
                .consultarArtistaPorID(
                    id.text.toString().toInt()
                )
            if(artista == null){
                mostrarSnackBar("Usu. no encontrado")
                nombre.setText("")
                canciones.setText("")
                nacimiento.setText("")
            }else{
                id.setText(artista.id.toString())
                nombre.setText(artista.nombre)
                canciones.setText(artista.canciones)
                nacimiento.setText(artista.nacimiento)
                mostrarSnackBar("Art. encontrado")
            }
        }
        val botonCrearBDD = findViewById<Button>(R.id.btn_crud_crear)
        botonCrearBDD.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.txt_crud_nombre)
            val canciones = findViewById<EditText>(R.id.txt_crud_canciones)
            val nacimiento = findViewById<EditText>(R.id.txt_fechaDeNacimiento)
            val respuesta = ArtistaBaseDatos.tablaArtista !!
                .crearArtista(
                    nombre.text.toString(),
                    canciones.text.toString(),
                    nacimiento.text.toString()
                )
            if(respuesta) mostrarSnackBar("Art. creado!")
        }
        val botonActualizarBDD = findViewById<Button>(R.id.btn_crud_actualizar)
        botonActualizarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.txt_crud_id)
            val nombre = findViewById<EditText>(R.id.txt_crud_nombre)
            val canciones = findViewById<EditText>(R.id.txt_crud_canciones)
            val nacimiento = findViewById<EditText>(R.id.txt_fechaDeNacimiento)
            val respuesta = ArtistaBaseDatos.tablaArtista !!
                .actualizarArtistaFormulario(
                    nombre.text.toString(),
                    canciones.text.toString(),
                    nacimiento.text.toString(),
                    id.text.toString().toInt()
                )
            if(respuesta) mostrarSnackBar("Arts. actualizado!")
        }
        val botonEliminarBDD = findViewById<Button>(R.id.btn_crud_eliminar)
        botonEliminarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.txt_crud_id)
            val respuesta = ArtistaBaseDatos.tablaArtista!!
                .eliminarArtistaFormulario(
                    id.text.toString().toInt()
                )
            if(respuesta) mostrarSnackBar("Arts. Eliminado")
        }
        val botonCanciones = findViewById<Button>(R.id.btn_ver_canciones)
        botonCanciones.setOnClickListener{
            irActividad(CancionesCRUD::class.java)
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
            findViewById(R.id.cl_crud_artista),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

}