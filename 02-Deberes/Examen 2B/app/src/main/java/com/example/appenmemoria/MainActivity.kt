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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //Inicializar bases de datos
        EBaseDatos.tablas = ESqliteHelper(this)
        val listView = findViewById<ListView>(R.id.lv_lisrtview_artista)
        val adaptador = ArtistaAdapter(this, EBaseDatos.tablas!!.consultarArtistas())
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()//notificar las actualizaciones a la interfaz
        registerForContextMenu(listView) ///se va usar menu contextual

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_artistas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonCrear = findViewById<Button>(R.id.btn_crear_artista)
        botonCrear.setOnClickListener{
            irActividad(ArtistaCrear::class.java,-1)
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

    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //lenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        posicionItemSeleccionado = position
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val artistaId = info.targetView.tag as Int

        return when (item.itemId){
            R.id.mi_editarArt -> {
                mostrarSnackbar("Editar $posicionItemSeleccionado")
                irActividad(ArtistaCrear::class.java, artistaId)
                return true
            }
            R.id.mi_eliminarArt -> {
                mostrarSnackbar(
                    "Eliminar $posicionItemSeleccionado")
                EBaseDatos.tablas!!.eliminarArtistaFormulario(artistaId)
                //BDatosMemoriaArt.eliminarArtista(artistaId)
                mostrarSnackbar("Artista con id: ${artistaId} eliminado")
                irActividad(MainActivity::class.java,-1)
                return true
            }
            R.id.mi_canciones -> {
                mostrarSnackbar(
                    "Canciones $posicionItemSeleccionado")
                irActividad(Canciones::class.java, artistaId)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_artistas),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

}