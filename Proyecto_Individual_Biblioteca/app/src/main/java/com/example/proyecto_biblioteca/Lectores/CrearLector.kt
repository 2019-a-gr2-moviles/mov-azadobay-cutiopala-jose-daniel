package com.example.proyecto_biblioteca.Lectores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyecto_biblioteca.Constantes
import com.example.proyecto_biblioteca.R
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_crear_lector.*
import com.github.kittinunf.result.Result

class CrearLector : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_lector)
        btn_ingresarLector.setOnClickListener {
            val nuevoLector = Lector(
                null,
                null,
                null,
                txt_ingresarNombreCliente.text.toString(),
                txt_ingresarApellidoLector.text.toString(),
                txt_ingresarCedulaLector.text.toString()

            )
            ingresarNuevoLector(nuevoLector)
        }
    }

    fun ingresarNuevoLector(lector: Lector) {

        val url = Constantes.ip + Constantes.lector

        val parametros = listOf(
            "nombre" to "${lector.nombre}",
            "apellido" to "${lector.apellido}",
            "cedula" to "${lector.cedula}"

        )

        url.httpPost(parametros)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            this.finish()
                        }
                    }
                }
            }
    }
}
