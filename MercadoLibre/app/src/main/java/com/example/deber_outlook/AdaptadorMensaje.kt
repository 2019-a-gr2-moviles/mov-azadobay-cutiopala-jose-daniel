package com.example.deber_outlook

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class AdaptadorMensaje (
    private val listaMensaje: List<Parcelable>,
    private val contexto: ReciclerView,
    private val recyclerView: RecyclerView
) :
    RecyclerView.Adapter<AdaptadorMensaje.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var nombreTextView: TextView
        var descriptionTextView: TextView
        var anuncioTextView: TextView
        var idUsuario: Int =0
        var imgFoto:ImageView
        var cabeceraTextView: String = ""
        var contenidoTextView: String = ""

        init {
            imgFoto=view.findViewById(R.id.img_foto) as ImageView
            nombreTextView = view.findViewById(R.id.txt_nombre) as TextView
            descriptionTextView = view.findViewById(R.id.txt_description) as TextView
            anuncioTextView = view.findViewById(R.id.txt_chat) as TextView


            val layout = view.findViewById(R.id.RelativeLayout) as RelativeLayout

            layout.setOnClickListener {
                val mensaje =Parcelable(nombreTextView.text.toString(),
                    descriptionTextView.text.toString(),
                    anuncioTextView.text.toString(),
                    idUsuario,
                    cabeceraTextView,
                    contenidoTextView

                )
                irAVerMensajeActivity(mensaje)

                    Log.i("recycler-view", "Layout presionado") }

            }
        fun irAVerMensajeActivity(mensaje:Parcelable){
            contexto.irAVerMensajeActivity(mensaje)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return listaMensaje.size
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorMensaje.MyViewHolder, position: Int) {
        val mensaje = listaMensaje[position]


        myViewHolder.nombreTextView.text = mensaje.autor
        myViewHolder.descriptionTextView.text = mensaje.mensaje
        myViewHolder.anuncioTextView.text = mensaje.anuncio
        myViewHolder.idUsuario = mensaje.id_usuario
        myViewHolder.cabeceraTextView=mensaje.cabecera
        myViewHolder.contenidoTextView=mensaje.recibir


        when (mensaje.id_usuario) {
            1 -> {
                myViewHolder.imgFoto.setImageResource(R.mipmap.samsung)
            }
            2 -> {
                myViewHolder.imgFoto.setImageResource(R.mipmap.huawei)
            }
            3 -> {
                myViewHolder.imgFoto.setImageResource(R.mipmap.xiaomi)
            }
        }

    }
}

