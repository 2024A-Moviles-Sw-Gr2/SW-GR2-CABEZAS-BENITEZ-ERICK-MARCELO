package com.example.a2024aswgr2cbem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ECrudEntrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ecrud_entrenador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_sqlite)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // buscar entrebnador
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcon = findViewById<EditText>(R.id.input_descripcion)
            val entrenador = EbaseDeDatos.tablaEntrenador !!
                .consultarEntrenadorPorID(
                    id.text.toString().toInt()
                )
            if(entrenador == null){
                mostrarSnackBar("Usu. no encontrado")
                id.setText("")
                nombre.setText("")
                descripcon.setText("")
            }else{
                id.setText(entrenador.id.toString())
                nombre.setText(entrenador.nombre)
                descripcon.setText(entrenador.descriptor)
                mostrarSnackBar("Usu. encontrado")
            }
        }
        val botonCearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCearBDD.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descriptor = findViewById<EditText>(R.id.input_descripcion)
            val respuesta = EbaseDeDatos.tablaEntrenador !!
                .crearEntrenador(
                    nombre.text.toString(),
                    descriptor.text.toString()
                )
            if(respuesta) mostrarSnackBar("Entr. creado!")
        }
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descriptor = findViewById<EditText>(R.id.input_descripcion)
            val respuesta = EbaseDeDatos.tablaEntrenador !!
                .actualizarEntrenadorFormulario(
                    nombre.text.toString(),
                    descriptor.text.toString(),
                    id.text.toString().toInt()
                )
            if(respuesta) mostrarSnackBar("Entr. actualizado!")
        }
        val botonEliminarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonEliminarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.input_id)
            val respuesta = EbaseDeDatos.tablaEntrenador!!
                .eliminarEntrenadorFormulario(
                    id.text.toString().toInt()
                )
            if(respuesta) mostrarSnackBar("Entr. Eliminado")
        }

    }
    fun mostrarSnackBar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_sqlite),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }



}
