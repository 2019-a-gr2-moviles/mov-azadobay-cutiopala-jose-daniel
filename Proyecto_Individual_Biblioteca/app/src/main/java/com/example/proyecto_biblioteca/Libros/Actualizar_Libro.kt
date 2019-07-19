package com.example.proyecto_biblioteca.Libros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyecto_biblioteca.Constantes
import com.example.proyecto_biblioteca.R
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar__libro.*
import kotlinx.android.synthetic.main.layout_libros.*

class Actualizar_Libro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar__libro)

        val tituloDeLibro: String? = this.intent.getStringExtra("zapato-marca")
        val autorDeLibro: String? = this.intent.getStringExtra("zapato-color")
        val editorialDeLibro: String? = this.intent.getStringExtra("zapato-tipo")
        val numeroEdicionLibro: String? = this.intent.getStringExtra("zapato-talla")
        val LibroId: Int? = this.intent.getIntExtra("zapato-id", -1)
        val stockDeLibro: Int? = this.intent.getIntExtra("zapato-cantidad", -1)


        Log.i(
            "http",
            "$LibroId $tituloDeLibro $autorDeLibro $numeroEdicionLibro $editorialDeLibro $stockDeLibro"
        )

        txt_actualizarId.text = LibroId.toString()
        txt_actual.hint = tituloDeLibro
        txt_actualizarActor.hint = autorDeLibro
        txt_actualizarEditorial.hint = editorialDeLibro
        txt_actualizarNumeroEditorial.hint = numeroEdicionLibro
        txt_actualizarStock.hint = stockDeLibro.toString()


        btn_actualizarLibro.setOnClickListener {
            val libroActualizado = Libro(
                null,
                null,
                txt_actualizarId.text.toString().toInt(),
                txt_actual.text.toString(),
                txt_actualizarActor.text.toString(),
                txt_actualizarEditorial.text.toString(),
                txt_actualizarNumeroEditorial.text.toString(),
                txt_actualizarStock.text.toString().toInt()

            )
            actualizarZapato(libroActualizado)
        }
    }

    fun actualizarZapato(libroActual: Libro) {

        val url = Constantes.ip + Constantes.libro + "/${libroActual.id}"
        Log.i("url","url to patch ${url}")

        val parametros = listOf(
            "titulo" to "${libroActual.titulo}",
        "autor" to "${libroActual.autor}",
        "editorial" to "${libroActual.editorial}",
        "numeroEdicion" to "${libroActual.numeroEdicion}",
        "stock" to "${libroActual.stock}"
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
            ListaLibros::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}