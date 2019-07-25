package com.example.proyecto_biblioteca.Solicitudes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_biblioteca.R
import kotlinx.android.synthetic.main.activity_menu_solicitudes.*

class MenuSolicitudes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_solicitudes)

        btn_nuevaReserva.setOnClickListener {
            CrearCompra()
        }
        btn_verReservas.setOnClickListener {
            ListaCompras()
        }
    }

    fun CrearCompra() {
        intent = Intent(
            this,
            CrearSolicitud::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun ListaCompras() {
        intent = Intent(
            this,
            ListaSolicitud::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
