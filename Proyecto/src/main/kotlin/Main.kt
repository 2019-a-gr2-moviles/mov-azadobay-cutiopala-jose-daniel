import Clases.Biblioteca
import Clases.Libro
import Clases.Persona
import java.awt.*;
import javax.swing.*;
import com.google.gson.Gson
import java.io.File
import java.io.IOException

var biblioteca = Biblioteca()
val gson = Gson();

fun main() {

    abrirBaseDatos()
//    val nuevaBiblioteca = insertarLibro(6, "PruebaLibro")
//    val nuevaBiblioteca = borrarLibro(6);
      biblioteca.libros.removeAt(1);
    cerrarBaseDatos(biblioteca);

}

fun abrirBaseDatos() {
    val content = File("src/main/resources/base.json").readText()
    biblioteca = gson.fromJson(content, Biblioteca::class.java)
}

fun cerrarBaseDatos(bibliotecaActualizada: Biblioteca) {
    val nuevaBiblioteca = gson.toJson(bibliotecaActualizada)
    val content2 = File("src/main/resources/base.json").writeText(nuevaBiblioteca);
}

fun insertarLibro(codigo: Int, nombre: String): Biblioteca {
    val libro = Libro()
    libro.codigo = codigo
    libro.nombre = nombre;

    biblioteca.libros.add(libro);
    return biblioteca
}

fun borrarLibro(codigo: Int) : Biblioteca{

    biblioteca.libros.forEach {
        if(it.codigo == codigo){
            println(it.codigo)
            var indice = biblioteca.libros.indexOf(it)
            println(indice);
        }
    }
    return biblioteca
}