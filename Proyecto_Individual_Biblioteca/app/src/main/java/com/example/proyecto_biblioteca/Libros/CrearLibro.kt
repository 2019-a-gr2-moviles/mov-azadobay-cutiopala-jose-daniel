package com.example.proyecto_biblioteca.Libros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyecto_biblioteca.Constantes
import com.example.proyecto_biblioteca.R
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

import kotlinx.android.synthetic.main.activity_crear_libro.*

class CrearLibro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_libro)

        btn_nuevoLibro.setOnClickListener {
            val libro = Libro(
                null,
                null,
                null,
                txt_actualizarTitulo.text.toString(),
                txt_autor.text.toString(),
                txt_editorial.text.toString(),
                txt_numeroEdicion.text.toString(),
                txt_stock.text.toString().toInt()
            )
            ingresarNuevoLibro(libro)
        }
    }

    fun ingresarNuevoLibro(libro: Libro) {

        val url = Constantes.ip + Constantes.libro
        val json = """
            {
            "titulo": "${libro.titulo}",
            "autor": "${libro.autor}",
            "editorial": "${libro.editorial}",
            "numeroEdicion": "${libro.numeroEdicion}",
            "stock": "${libro.stock}"


            }
                    """

        Log.i("http-json", json)

        url.httpPost().body(json)
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

//    fun irListaLibros() {
//        intent = Intent(
//            this,
//            MenuLibros  ::class.java
//        )
//        startActivity(intent)
//    }
}


