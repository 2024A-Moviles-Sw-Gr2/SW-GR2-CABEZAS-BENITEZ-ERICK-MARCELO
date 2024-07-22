package com.example.crudentidad

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class BListArtistas : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var artistaDatabaseHelper: ESqliteArtista


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_blist_artistas)

        artistaDatabaseHelper = ESqliteArtista(this)

        // Aquí puedes llamar a métodos de la base de datos
        val listartistas = artistaDatabaseHelper.consultarArtistasBasico()
        // Maneja los artistas como necesites

        listView = findViewById<ListView>(R.id.lv_artistas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listartistas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()// notificar actualizaciones en la interfaz
        registerForContextMenu(listView) //se va usar menu contextual

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lv_artistas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
        return when (item.itemId){
            R.id.mi_editarartista -> {
                mostrarSnackbar(
                    "Editar $posicionItemSeleccionado")
                return true
            }
            R.id.mi_eliminarartista -> {
                mostrarSnackbar(
                    "Editar $posicionItemSeleccionado")
                //añadir funcionalidad
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.lv_artistas),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

}