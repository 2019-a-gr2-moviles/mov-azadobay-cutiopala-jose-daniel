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

class Actualizar_Libro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar__libro)

        val zapatoMarca: String? = this.intent.getStringExtra("zapato-marca")
        val zapatoColor: String? = this.intent.getStringExtra("zapato-color")
        val zapatoTipo: String? = this.intent.getStringExtra("zapato-tipo")
        val zapatoTalla: String? = this.intent.getStringExtra("zapato-talla")
        val zapatoId: Int? = this.intent.getIntExtra("zapato-id", -1)
        val zapatoCantidad: Int? = this.intent.getIntExtra("zapato-cantidad", -1)


        Log.i(
            "http",
            "$zapatoId $zapatoMarca $zapatoColor $zapatoTalla $zapatoTipo $zapatoCantidad"
        )

        actId.text = zapatoId.toString()
        actTit.hint = zapatoMarca
        actAut.hint = zapatoColor
        actEdit.hint = zapatoTipo
        actNumEd.hint = zapatoTalla.toString()
        actStock.hint = zapatoCantidad.toString()


        btnEliminar.setOnClickListener {
            val zapato = Libro(
//                null,
                null,
                null,
                actId.text.toString().toInt(),
                actTit.text.toString(),
                actAut.text.toString(),
                actEdit.text.toString(),
                actNumEd.text.toString(),
                actStock.text.toString().toInt()

            )
            actualizarZapato(zapato)
        }
    }

    fun actualizarZapato(zapato: Libro) {

        val url = Constantes.ip + Constantes.libro + "/${zapato.id}"
        Log.i("url","url to patch ${url}")

        val parametros = listOf(
            "titulo" to "${zapato.titulo}",
        "autor" to "${zapato.autor}",
        "editorial" to "${zapato.editorial}",
        "numeroEdicion" to "${zapato.numeroEdicion}",
        "stock" to "${zapato.stock}"
        )



        /*
        val json = """
            {
            "titulo": "${zapato.titulo}",
            "autor": "${zapato.autor}",
            "editorial": "${zapato.editorial}",
            "numeroEdicion": "${zapato.numeroEdicion}",
            "stock": "${zapato.stock}"

                                   }
                    """*/

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