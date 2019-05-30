package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_parcelable.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_parcelable.setOnClickListener {
            irAParcelable()
        }
        btn_toast.setOnClickListener {
            irAToast()
        }
        btn_adapter.setOnClickListener {
            irAListView()
        }
    }

    fun irAToast(){
        val intentExplicito = Intent(
            this,
            Main2Activity::class.java
        )
        startActivity(intentExplicito)
    }

    fun irAListView(){
        val intentExplicito = Intent(
            this,
            ListViewActivity::class.java
        )
        startActivity(intentExplicito)
    }


    fun irAParcelable(){
        val intentExplicito = Intent(
            this,
            Parcelable::class.java
        )
        val adrian = Usuario("Adrian",
            29,
            Date(),
            12.12)
        intentExplicito.putExtra("usuario",adrian)

        val cachetes = Mascota("Cachetes", adrian)
        intentExplicito.putExtra("mascota", cachetes)


        startActivity(intentExplicito)


    }




}
