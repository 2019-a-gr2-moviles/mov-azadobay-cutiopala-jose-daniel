package com.example.proyecto_biblioteca.Lectores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.beust.klaxon.Klaxon
import com.example.proyecto_biblioteca.Constantes
import com.example.proyecto_biblioteca.R
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_lista_lector.*
import java.lang.Exception

class ListaLector : AppCompatActivity() {

    private val listaClientes: ArrayList<Lector> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_lector)
        obtenerClientes()

    }

    fun iniciarRecyclerView(listaClientes: ArrayList<Lector>,
                            actividad: ListaLector,
                            recyclerView: androidx.recyclerview.widget.RecyclerView) {
        val adaptadorCliente = AdaptadorListaLector(
            listaClientes,
            actividad,
            recyclerView)
        rv_listalector.adapter = adaptadorCliente
        rv_listalector.itemAnimator = DefaultItemAnimator()
        rv_listalector.layoutManager = LinearLayoutManager(actividad)

        adaptadorCliente.notifyDataSetChanged()
    }

    fun obtenerClientes() {
        //this.listaClientes.clear()
        try {
            val url = (Constantes.ip + Constantes.lector)
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

                            val clientes = Klaxon()
                                .parseArray<Lector>(data)

                            clientes?.forEach { cliente ->
                                (
                                        this.listaClientes.add(cliente)
                                        )
                            }
                            runOnUiThread {
                                iniciarRecyclerView(listaClientes, this, rv_listalector)
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            Log.i("http", "${e}")
        }
    }

    fun eliminarCliente(cliente: Lector) {
        val url = "${Constantes.ip}${Constantes.lector}/${cliente.id}"

        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            irListaClientes()
                        }
                    }
                }
            }
    }

    fun irListaClientes() {
        intent = Intent(
            this,
            ListaLector::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irActulizarCliente(cliente: Lector) {
        intent = Intent(
            this,
            ActualizarLector::class.java
        )
        intent.putExtra("cliente-nombre", cliente.nombre)
        intent.putExtra("cliente-apellido", cliente.apellido)
        intent.putExtra("cliente-cedula", cliente.cedula)
        intent.putExtra("cliente-id", cliente.id as Int)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
