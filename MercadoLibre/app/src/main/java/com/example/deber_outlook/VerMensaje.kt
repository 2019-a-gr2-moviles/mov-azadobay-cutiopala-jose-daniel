package com.example.deber_outlook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ver_mensaje.*

class VerMensaje : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_mensaje)

        val mensaje:Parcelable?= this.intent.getParcelableExtra<Parcelable>("mensaje")

        if(mensaje?.id_usuario==1){
            img_anuncio.setImageResource(R.mipmap.samsung)
            //img_icon.setImageResource(R.mipmap.icon1)

        }
        else if(mensaje?.id_usuario==2){
            img_anuncio.setImageResource(R.mipmap.huawei)
        }
        else{
            img_anuncio.setImageResource(R.mipmap.xiaomi)
            //img_icon.setImageResource(R.mipmap.icon2)
        }

        txt_nombre1.text = mensaje?.autor
        txt_contenido2.text = mensaje?.mensaje
        txt_contenido.text = mensaje?.anuncio
    }
}
