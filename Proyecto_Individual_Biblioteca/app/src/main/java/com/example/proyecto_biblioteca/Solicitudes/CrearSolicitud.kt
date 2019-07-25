package com.example.proyecto_biblioteca.Solicitudes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.example.proyecto_biblioteca.Constantes
import com.example.proyecto_biblioteca.Lectores.ClienteAuxiliar
import com.example.proyecto_biblioteca.Lectores.Lector
import com.example.proyecto_biblioteca.Libros.Libro
import com.example.proyecto_biblioteca.Libros.LibroAuxiliar
import com.example.proyecto_biblioteca.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_solicitud.*
import java.lang.Exception

class CrearSolicitud : AppCompatActivity() {

    private var listaClientes: ArrayList<Lector>
    private var listaZapatos: ArrayList<Libro>

    init {
        listaZapatos = arrayListOf<Libro>()
        listaClientes = arrayListOf<Lector>()

        obtenerZapatos()
        obtenerClientes()
    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_solicitud)


            btn_crearSolicitud.setOnClickListener {
                try {

                    val zapato = listaZapatos.find { zapato ->
                        zapato.id == txt_crearSolicitudLibro.text.toString().toInt()
                    }

                    val cliente = listaClientes.find { cliente ->
                        cliente.cedula == txt_crearSolicitudCedula.text.toString()
                    }

                    if (zapato != null && cliente != null) {
                        val fechaActual = "2019/07/24"
//                        val total: Double = zapato.precio * (txt_ing_can_zap_com.text.toString().toInt())
                        val compra = Solicitud(
                            null,
                            fechaActual,
                            //null
                            ClienteAuxiliar(),
                            LibroAuxiliar()
                        )


                        ingresarCompra(compra)
                        Toast.makeText(this, "Compra ingresada correctamente", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(this, "Cédula o código del zapato no valido", Toast.LENGTH_LONG).show();
                    }
//            }
                } catch (e: Exception) {
                    Toast.makeText(this, "Cédula o código del zapato no valido", Toast.LENGTH_LONG).show();
                }
            }
        }

        fun obtenerZapatos() {
            val url = "${Constantes.ip}${Constantes.libro}"
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
//                        Log.i("http", "Data: ${data}")

                            val zapatos = Klaxon()
                                .parseArray<Libro>(data)

                            zapatos?.forEach { zapato ->
                                (
                                        this.listaZapatos.add(zapato)
                                        )
                            }
                        }
                    }
                }
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
//                            Log.i("http", "Data: ${data}")

                                val clientes = Klaxon()
                                    .parseArray<Lector>(data)

                                clientes?.forEach { cliente ->
                                    (
                                            this.listaClientes.add(cliente)
                                            )
                                }
                            }
                        }
                    }
            } catch (e: Exception) {
                Log.i("http", "${e}")
            }
        }

        fun ingresarCompra(compra: Solicitud) {
            val url = Constantes.ip + Constantes.compra
            val json = """
            {
            "fecha": "${compra.fecha}",
            "idLibro": ${compra.idLector!!.id},
            "idLector": ${compra.idLibro!!.id}
                        }
                    """

            url.httpPost().body(json)
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                        }
                        is Result.Success -> {
                            runOnUiThread {
                                //irListaCompras()
                                this.finish()
                            }
                        }
                    }
                }
        }

//        fun irListaCompras() {
//            intent = Intent(
//                this,
//                ListaSolicitud::class.java
//            )
//            startActivity(intent)
//        }
    }
