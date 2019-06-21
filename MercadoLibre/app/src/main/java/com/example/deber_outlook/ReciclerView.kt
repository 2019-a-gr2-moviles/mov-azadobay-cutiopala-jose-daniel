package com.example.deber_outlook

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recicler_view.*

class ReciclerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicler_view)

        var listaInicio = arrayListOf<Parcelable>()

        for(num in 1..10) {

            var add = Parcelable(
                    "Telefono Celular Samsung A10 A20 A30 A50 A70 A80",
                    "U$150",
                    "Nuevo  -  25 vendidos",
                    1,
                    "CEC-EPN descuentos para graduados",
                    "para jennyk-castro@hotmail.com"
            )

            var addNew = Parcelable(
                "Huawei Mate 20 Lite +mica+estuche+envió Gratis",
                "U$249",
                "Nuevo  -  1 vendido",
                2,
                "Un amigo quiere que indiques que te gusta una página en Facebook",
                "para Jenny Castro"
                )
            var add3 = Parcelable(
                "Telefonos Xiaomi Mi, Redmi Y Note 7 De 64gb Y 128 Gb",
                "U$165",
                "Nuevo  -  34 vendidos",
                3,
                "Un amigo quiere que indiques que te gusta una página en Facebook",
                "para Jenny Castro"
            )

            listaInicio.add(add)
            listaInicio.add(addNew)
            listaInicio.add(add3)
        }


        iniciarRecylerView(listaInicio, this,rv_mensaje)
    }

    fun iniciarRecylerView(
        lista: List<Parcelable>,
        actividad: ReciclerView,
        recycler_view: RecyclerView
    ) {
        val adaptadorMensaje = AdaptadorMensaje(
            lista,
            actividad,
            recycler_view
        )
        recycler_view.adapter = adaptadorMensaje
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.layoutManager = LinearLayoutManager(actividad)

        adaptadorMensaje.notifyDataSetChanged()
    }

    fun irAVerMensajeActivity(mensaje: Parcelable){
        val intentExplicito = Intent(
            this,
            VerMensaje::class.java
        )

        intentExplicito.putExtra("mensaje",mensaje)
        startActivity(intentExplicito)
    }

}
