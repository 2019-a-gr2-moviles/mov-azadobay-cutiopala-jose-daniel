package com.example.proyecto_biblioteca.Lectores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.proyecto_biblioteca.R


class AdaptadorListaLector (private val listaClientes: ArrayList<Lector>,
                            private val contexto: ListaLector,
                            private val recyclerView: androidx.recyclerview.widget.RecyclerView
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AdaptadorListaLector.MyViewHolder>(){

    inner class MyViewHolder(view: View) :  androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var apellidoTextView: TextView
        var cedulaTextView: TextView
        var idTextView: TextView
        var eliminarBoton: Button

        init {
            nombreTextView = view.findViewById(R.id.txt_nombreDelLector) as TextView
            apellidoTextView = view.findViewById(R.id.txt_apellidoDelLector) as TextView
            cedulaTextView = view.findViewById(R.id.txt_cedulaDelLector) as TextView
            idTextView = view.findViewById(R.id.txt_idDelLector) as TextView
            eliminarBoton = view.findViewById(R.id.btn_eliminarLector) as Button

            val layout = view.findViewById(R.id.layout_lector) as RelativeLayout
            layout.setOnClickListener {
                val cliente = crearCliente(
                    idTextView.text.toString().toInt(),
                    nombreTextView.text.toString(),
                    apellidoTextView.text.toString(),
                    cedulaTextView.text.toString()
                )
                contexto.irActulizarCliente(cliente)

            }

            eliminarBoton.setOnClickListener {
                val cliente = crearCliente(
                    idTextView.text.toString().toInt(),
                    nombreTextView.text.toString(),
                    apellidoTextView.text.toString(),
                    cedulaTextView.text.toString()
                )
                contexto.eliminarCliente(cliente)

            }
        }

    }

    //Esta función define el template que vamos a utilizar.
    // El template esta en la carpeta de recursos res/layout -> layout
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorListaLector.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_lectores,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    //Devuelve el número de items de la lista
    override fun getItemCount(): Int {
        return listaClientes.size
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorListaLector.MyViewHolder, position: Int) {
        val cliente: Lector = listaClientes[position]
        myViewHolder.nombreTextView.text = cliente.nombre
        myViewHolder.apellidoTextView.text = cliente.apellido
        myViewHolder.cedulaTextView.text = cliente.cedula
        myViewHolder.idTextView.text = cliente.id.toString()

    }

    fun crearCliente(id: Int,
                     nombre: String,
                     apellido: String,
                     cedula: String): Lector {
        val cliente = Lector(
//            null,
            null,
            null,
            id,
            nombre,
            apellido,
            cedula
        )
        return cliente
    }
}