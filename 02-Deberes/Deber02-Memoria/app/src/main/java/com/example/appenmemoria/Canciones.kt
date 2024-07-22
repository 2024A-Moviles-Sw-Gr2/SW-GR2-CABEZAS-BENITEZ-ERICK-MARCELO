package com.example.appenmemoria

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class Canciones : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_canciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_canciones_art)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        val artistaId = intent.getIntExtra("artista_id", -1)
        //
        val listView = findViewById<ListView>(R.id.lv_listCanciones)
        val adaptador = CancionAdapter(this, BDatosMemoriaSong.cancionesPorArtista(artistaId))
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()//notificar las actualizaciones a la interfaz
        registerForContextMenu(listView) ///se va usar menu contextual

        val botonCrear = findViewById<Button>(R.id.btn_crear_canciones)
        botonCrear.setOnClickListener{
            irActividad(CancionCrear::class.java,artistaId, -1)
        }

        val botonRegresar = findViewById<Button>(R.id.btn_regresar)
        botonRegresar.setOnClickListener{
            irActividad(MainActivity::class.java,-1, -1)
        }
    }
    fun irActividad(
        clase: Class<*>,
        artistaId: Int?,
        cancionId: Int
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("artista_id", artistaId)
        intent.putExtra("cancion_id", cancionId)
        startActivity(intent)
    }

    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //lenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menucancion, menu)
        //Obtener id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        posicionItemSeleccionado = position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val tag = info.targetView.tag as CancionTag
        val cancionId = tag.cancionId
        val artistaId = tag.artistaId

        return when (item.itemId){
            R.id.mi_editarCancion -> {
                mostrarSnackbar("Editar $posicionItemSeleccionado")
                irActividad(CancionCrear::class.java, artistaId, cancionId ?: -1)
                return true
            }
            R.id.mi_eliminarCancion -> {
                mostrarSnackbar("Eliminar $posicionItemSeleccionado")
                if (cancionId != null) {
                    BDatosMemoriaSong.eliminarCancion(cancionId)
                    mostrarSnackbar("Canción con id: $cancionId eliminada")
                }
                irActividad(Canciones::class.java, artistaId, -1)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_canciones_art),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}