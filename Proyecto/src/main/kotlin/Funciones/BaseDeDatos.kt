package Funciones
import Clases.Biblioteca
import com.google.gson.Gson
import java.io.File

var biblioteca = Biblioteca()
val gson = Gson();

fun abrirBaseDatos():Biblioteca {
    val content = File("src/main/resources/base.json").readText()
    biblioteca = gson.fromJson(content, Biblioteca::class.java)
    return biblioteca
}

fun actualizarBaseDatos(bibliotecaActualizada: Biblioteca) {
    val nuevaBiblioteca = gson.toJson(bibliotecaActualizada)
    val content2 = File("src/main/resources/base.json").writeText(nuevaBiblioteca);
    println(content2);
}
