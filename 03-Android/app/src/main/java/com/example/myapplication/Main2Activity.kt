package com.example.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View

import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view: View ->
            Snackbar
                .make(view, "Gracias por crear un nuevo usuario", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
            crearEntrenador(view)
        }
    }

    fun crearEntrenador(view: View){
        Snackbar
            .make(view, "Gracias por crear un nuevo usuario", Snackbar.LENGTH_LONG)
            .setAction("Action", null)

    }

}
