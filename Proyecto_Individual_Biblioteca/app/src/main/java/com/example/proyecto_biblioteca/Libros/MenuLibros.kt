package com.example.proyecto_biblioteca.Libros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_biblioteca.R
import kotlinx.android.synthetic.main.activity_menu_libros.*

class MenuLibros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_libros)

        btn_consultar_libros.setOnClickListener {
            irVerLibros()
        }

        btn_nuevoLibro.setOnClickListener {
            anadirLibro()
        }
    }

    fun irVerLibros(){
        intent = Intent(
            this,
            ListaLibros::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)

    }

    fun anadirLibro(){
        intent = Intent(
            this,
            CrearLibro::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
