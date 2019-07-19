package com.example.proyecto_biblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_biblioteca.Libros.MenuLibros
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_libros.setOnClickListener {
            irMenuLibros()
        }

        btn_lectores.setOnClickListener {
            irMenuLectores()
        }
        btn_solicitudes.setOnClickListener {
            irMenuSolicitudes()
        }
    }

    fun irMenuLibros() {
        val intent = Intent(
            this,
            MenuLibros::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irMenuLectores() {
        val intent = Intent(
            this,
            MenuLectores::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irMenuSolicitudes() {
        val intent = Intent(
            this,
            MenuSolicitudes::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
