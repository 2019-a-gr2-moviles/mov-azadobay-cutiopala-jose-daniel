package com.example.proyecto_biblioteca.Libros

import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.proyecto_biblioteca.R
import kotlinx.android.synthetic.main.layout_libros.view.*


class AdaptadorListaLibros(private val listaLibros: ArrayList<Libro>,
                           private val contexto: ListaLibros,
                           private val recyclerView: androidx.recyclerview.widget.RecyclerView
): androidx.recyclerview.widget.RecyclerView.Adapter<AdaptadorListaLibros.MyViewHolder>(){

    inner class MyViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        var idTextView: TextView
        var tituloTextView: TextView
        var autorTextView: TextView
        var editorialTextView: TextView
        var numeroEdicion: TextView
        var stockTextView: TextView
        var eliminarBoton: Button



        init {

            idTextView = view.findViewById(R.id.txt_id_libro) as TextView
            tituloTextView = view.findViewById(R.id.txt_titulo) as TextView
            autorTextView = view.findViewById(R.id.txt_autor) as TextView
            editorialTextView = view.findViewById(R.id.txt_editorial) as TextView
            numeroEdicion = view.findViewById(R.id.txt_numeroEdicion) as TextView
            stockTextView = view.findViewById(R.id.txt_stock) as TextView
            eliminarBoton = view.findViewById(R.id.btn_eliminarLibro) as Button

            val layout = view.findViewById(R.id.layout) as RelativeLayout
            layout.setOnClickListener {

                val nuevoLibro = nuevoLibro(
                    idTextView.text.toString().toInt(),
                    tituloTextView.text.toString(),
                    autorTextView.text.toString(),
                    editorialTextView.text.toString(),
                    numeroEdicion.text.toString(),
                    stockTextView.text.toString().toInt()
                )
                contexto.irActulizarLibro(nuevoLibro)
            }
            eliminarBoton.setOnClickListener {

                val libro = nuevoLibro(
                    idTextView.text.toString().toInt(),
                    tituloTextView.text.toString(),
                    autorTextView.text.toString(),
                    editorialTextView.text.toString(),
                    numeroEdicion.text.toString(),
                    stockTextView.text.toString().toInt()
                )
                contexto.eliminarLibro(libro)
            }
        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorListaLibros.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_libros,
                p0,
                false
            )
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return listaLibros.size
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorListaLibros.MyViewHolder, position: Int){
        val libros: Libro = listaLibros[position]
        myViewHolder.idTextView.text = libros.id.toString()
        myViewHolder.tituloTextView.text = libros.titulo
        myViewHolder.autorTextView.text = libros.autor
        myViewHolder.editorialTextView.text = libros.editorial
        myViewHolder.numeroEdicion.text = libros.numeroEdicion
        myViewHolder.stockTextView.text = libros.stock.toString()
    }

    fun nuevoLibro(
        id: Int,
        titulo: String,
        autor: String,
        numeroEdicion: String,
        editorial: String,
        stock: Int
    ): Libro {
        val libro = Libro(
            null,
            null,
            id,
            titulo,
            autor,
            numeroEdicion,
            editorial,
            stock
        )
        return libro
    }

}





