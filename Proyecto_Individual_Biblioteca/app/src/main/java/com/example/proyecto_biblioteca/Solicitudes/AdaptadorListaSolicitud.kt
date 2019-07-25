package com.example.proyecto_biblioteca.Solicitudes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyecto_biblioteca.R

class AdaptadorListaSolicitud (private val listaCompras: ArrayList<Solicitud>,
                               private val contexto: ListaSolicitud,
                               private val recyclerView: androidx.recyclerview.widget.RecyclerView
): androidx.recyclerview.widget.RecyclerView.Adapter<AdaptadorListaSolicitud.MyViewHolder>() {

    inner class MyViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var idTextView: TextView
        var nombreTextView: TextView
        var apellidoTextView: TextView
        var cedulaTextView: TextView
        var zapatoTextView: TextView
        var fechaTextView: TextView
        var validezTextView: TextView
        var anularBoton: Button

        init {
            idTextView = view.findViewById(R.id.txt_idSolicitud) as TextView
            nombreTextView = view.findViewById(R.id.txt_nombreSolicitud) as TextView
            apellidoTextView = view.findViewById(R.id.txt_apellidoSolicitud) as TextView
            cedulaTextView = view.findViewById(R.id.txt_cedulaSolicitud) as TextView
            zapatoTextView = view.findViewById(R.id.txt_libroSolicitud) as TextView
            fechaTextView = view.findViewById(R.id.txt_fechaSolicitud) as TextView
            validezTextView = view.findViewById(R.id.txt_prestamoSolicitud) as TextView
            anularBoton = view.findViewById(R.id.btn_devolucion) as Button

            anularBoton.setOnClickListener {
                if (!validezTextView.text.toString().equals("No valida")) {
                    val compra = Solicitud(
                        idTextView.text.toString().toInt(),
                        null,
                        null,
                        null,
                        null
                    )
                    contexto.actualizarCompra(compra)
                } else {
                    Toast.makeText(contexto, "Factura invalida", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorListaSolicitud.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_solicitud,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaCompras.size
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorListaSolicitud.MyViewHolder, position: Int) {
        val compra: Solicitud = listaCompras[position]
        myViewHolder.idTextView.text = compra.id.toString()
        myViewHolder.nombreTextView.text = compra.codigoCli!!.nombre
        myViewHolder.apellidoTextView.text = compra.codigoCli!!.apellido
        myViewHolder.cedulaTextView.text = compra.codigoCli!!.cedula
        myViewHolder.fechaTextView.text = compra.fecha
        myViewHolder.zapatoTextView.text = "${compra.codigoZap!!.titulo}"

        var validez = "No valida"
        if (compra.validez!!) {
            validez = "Valida"
        }
        myViewHolder.validezTextView.text = validez
    }
}