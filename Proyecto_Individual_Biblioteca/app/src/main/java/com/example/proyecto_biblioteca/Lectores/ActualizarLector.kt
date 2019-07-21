package com.example.proyecto_biblioteca.Lectores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyecto_biblioteca.Constantes
import com.example.proyecto_biblioteca.R
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_lector.*

class ActualizarLector : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_lector)

        val clienteNombre: String? = this.intent.getStringExtra("cliente-nombre")
        val clienteApellido: String? = this.intent.getStringExtra("cliente-apellido")
        val clienteCedula: String? = this.intent.getStringExtra("cliente-cedula")
        val clienteId: Int? = this.intent.getIntExtra("cliente-id", -1)

        txt_actualizarNombre.hint = clienteNombre
        txt_actualizarApellido.hint = clienteApellido
        txt_actualizarCedual.hint = clienteCedula
        txt_actualizarId.text = clienteId.toString()

        btn_actualizarLector.setOnClickListener {
            val cliente =
                Lector(
                    null,
                    null,
                    txt_actualizarId.text.toString().toInt(),
                    txt_actualizarNombre.text.toString(),
                    txt_actualizarApellido.text.toString(),
                    txt_actualizarCedual.text.toString()
                )
            actualizarLector(cliente)

        }
    }

    fun actualizarLector(libroActual: Lector) {

        val url = Constantes.ip + Constantes.lector + "/${libroActual.id}"
        Log.i("url","url to patch ${url}")

        val parametros = listOf(
            "nombre" to "${libroActual.nombre}",
            "apellido" to "${libroActual.apellido}",
            "cedula" to "${libroActual.cedula}"

        )

        url.httpPut(parametros)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        Log.i("http", "$response")
                        runOnUiThread {
                            irListaZapatos()
                            this.finish()
                        }
                    }
                }
            }
    }

    fun irListaZapatos() {
        intent = Intent(
            this,
            ListaLector::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
