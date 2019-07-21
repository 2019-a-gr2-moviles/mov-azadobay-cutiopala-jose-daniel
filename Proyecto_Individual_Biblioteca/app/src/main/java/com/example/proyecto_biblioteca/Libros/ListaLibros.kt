package com.example.proyecto_biblioteca.Libros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.proyecto_biblioteca.Constantes
import com.example.proyecto_biblioteca.R
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_lista_libros.*

class ListaLibros : AppCompatActivity() {

    private val listaLibros: ArrayList<Libro> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_libros)

        obtenerLibros()
    }

    fun iniciarRecyclerView(listaLibroAdaptador: ArrayList<Libro>,
                            actividad: ListaLibros,
                            recyclerView: androidx.recyclerview.widget.RecyclerView
    ) {
        val adaptadorLibro = AdaptadorListaLibros(listaLibroAdaptador, actividad, recyclerView)
        rv_libros.adapter = adaptadorLibro
        rv_libros.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_libros.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

        adaptadorLibro.notifyDataSetChanged()
    }

    fun obtenerLibros() {

        val url = (Constantes.ip + Constantes.libro)
        Log.i("http", url)
        url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")

                        val libros = Klaxon()
                            .parseArray<Libro>(data)

                        libros?.forEach { libro ->
                            (
                                    this.listaLibros.add(libro)
                                    )
                        }
                        runOnUiThread {
                            iniciarRecyclerView(listaLibros, this, rv_libros)
                        }
                    }
                }
            }
    }

    fun irActulizarLibro(libro: Libro) {
        intent = Intent(
            this,
            Actualizar_Libro::class.java
        )
        intent.putExtra("zapato-marca", libro.titulo)
        intent.putExtra("zapato-color", libro.autor)
        intent.putExtra("zapato-talla", libro.numeroEdicion)
        intent.putExtra("zapato-tipo", libro.editorial)
        intent.putExtra("zapato-cantidad", libro.stock)
        intent.putExtra("zapato-id", libro.id)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun eliminarLibro(libro: Libro) {
        val url = "${Constantes.ip}${Constantes.libro}/${libro.id}"

        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            irListadoLibros()
                        }
                    }
                }
            }
    }

    fun irListadoLibros() {
        intent = Intent(
            this,
            ListaLibros::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
