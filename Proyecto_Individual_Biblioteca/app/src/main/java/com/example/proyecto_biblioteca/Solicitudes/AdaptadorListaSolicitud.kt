package com.example.proyecto_biblioteca.Solicitudes

import android.view.ViewGroup

class AdaptadorListaSolicitud (
    private val listaCompras: ArrayList<Solicitud>,
    private val contexto: ListaSolicitud,
    private val recyclerView: androidx.recyclerview.widget.RecyclerView

) : androidx.recyclerview.widget.RecyclerView.Adapter<AdaptadorListaSolicitud.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorListaSolicitud.MyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: AdaptadorListaSolicitud.MyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}