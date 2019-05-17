package Funciones
import Clases.Biblioteca
import javax.swing.JOptionPane

//var biblioteca= Biblioteca()
//
//fun iniciarPersonas(bibliotecaEx: Biblioteca){
//    biblioteca2= bibliotecaEx
//}

fun imprimir(){
    biblioteca.personas.forEach {
        println(it.nombre);
    }
}