package com.example.proyecto_biblioteca.Lectores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_biblioteca.R
import kotlinx.android.synthetic.main.activity_menu_lectores.*

class MenuLectores : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_lectores)

        btn_listaLectores.setOnClickListener {
            irVerLectores()
        }

        btn_nuevoLector.setOnClickListener {
            anadirLector()
        }
    }

    fun irVerLectores(){
        intent = Intent(
            this,
            ListaLector::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)

    }

    fun anadirLector(){
        intent = Intent(
            this,
            CrearLector::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
