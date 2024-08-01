package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerViewStates: RecyclerView = findViewById(R.id.recyclerViewStates)
        val recyclerViewMessages: RecyclerView = findViewById(R.id.recyclerViewMessages)

        // Configurar el RecyclerView horizontal para los estados
        recyclerViewStates.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val states = listOf(
            State(R.drawable.human7, "Xavicom"),
            State(R.drawable.human6, "Lau Charles"),
            State(R.drawable.human2, "Ingrit"),
            State(R.drawable.human1, "Dann Brun"),
            State(R.drawable.human10, "Sammy ❤️"),
            State(R.drawable.human5, "Nael Rudi"),
            State(R.drawable.human9, "George")
        )
        recyclerViewStates.adapter = StateAdapter(states)

        // Configurar el RecyclerView vertical para los mensajes
        recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        val messages = listOf(
            Message(R.drawable.human10, "Sammy ❤️", "Hola como estas?"),
            Message(R.drawable.human11, "Daniel Mera", "Eres un pro mardix"),
            Message(R.drawable.human8, "Kenny", "Si vamos con la Brit"),
            Message(R.drawable.human7, "Xavicom", "ya no me hables BB"),
            Message(R.drawable.human9, "George", "Enviado"),
            Message(R.drawable.human1, "Joss Darle", "Respondió tu historia"),
            Message(R.drawable.human3, "Dann Brun", "Enviado"),
            Message(R.drawable.human4, "Emma Magic", "jajajaja"),
            Message(R.drawable.human5, "Nael Rudi", "oeee si fuiste al evento?"),
            Message(R.drawable.human2, "Ingrit 🦋", "Respondió a tu historia"),
            Message(R.drawable.human6, "Lau Charles", "😘")


        )
        recyclerViewMessages.adapter = MessageAdapter(messages)
    }
}