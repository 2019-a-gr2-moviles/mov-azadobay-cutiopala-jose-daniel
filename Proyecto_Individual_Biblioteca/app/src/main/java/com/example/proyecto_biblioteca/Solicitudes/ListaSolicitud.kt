package com.example.proyecto_biblioteca.Solicitudes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.beust.klaxon.Klaxon
import com.example.proyecto_biblioteca.Constantes
import com.example.proyecto_biblioteca.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_lista_solicitud.*

class ListaSolicitud : AppCompatActivity() {

    private val listaCompras: ArrayList<Solicitud> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_solicitud)
        obtenerCompras()
    }

    fun iniciarRecyclerView(listaCompras: ArrayList<Solicitud>, actividad: ListaSolicitud, recyclerView: androidx.recyclerview.widget.RecyclerView) {
        val adaptadorCompra = AdaptadorListaSolicitud(listaCompras, actividad, recyclerView)
        rv_listaSolicitud.adapter = adaptadorCompra
        rv_listaSolicitud.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_listaSolicitud.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

        adaptadorCompra.notifyDataSetChanged()
    }

    fun obtenerCompras() {

        val url = (Constantes.ip + Constantes.compra)
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

                        ///////
                        val compras = Klaxon()
                            .parseArray<Solicitud>(data)

                        compras?.forEach { compra ->
                            (
                                    this.listaCompras.add(compra)

                                    )
                        }

                        Log.i("http","Hola: ${this.listaCompras[0].idLector.nombre.toString()}")




                        runOnUiThread {
                            iniciarRecyclerView(listaCompras, this, rv_listaSolicitud)
                        }
                    }
                }
            }
    }

    fun actualizarCompra(compra: Solicitud) {
//        val url = Constantes.ip + Constantes.compra + "/${compra.id}"
//        val json = """
//            {
//            "prestamo": "${compra.validez}"
//            }
//                    """
//        Log.i("http", url)
//        Log.i("http", json)
//        url.httpPut().body(json)
//            .responseString { request, response, result ->
//                when (result) {
//                    is Result.Failure -> {
//                        val ex = result.getException()
//                        Log.i("http", "Error: ${ex.message}")
//                    }
//                    is Result.Success -> {
//                        runOnUiThread {
//                            irListaCompras()
//                        }
//                    }
//                }
//            }
    }

    fun irListaCompras() {
        intent = Intent(
            this,
            ListaSolicitud::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}

