import Clases.Libro
import Clases.MyIcon
import Clases.Reserva
import Funciones.*
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.JsonSerializer
import java.util.*
import javax.swing.JOptionPane
import kotlin.collections.ArrayList

fun main() {
    abrirBaseDatos()
//    Funciones.imprimir()
    menuInicial()

//    buscarPersona("1723882039");
//    guardarDatosRenta(2,2)
//    filtrarReservasDeUnCliente("1723882039")
}

fun menuInicial() {

    while (true) {
        val icon = MyIcon()
        val seleccion =
            JOptionPane.showInputDialog(
                null, "SISTEMA DE BIBLIOTECA"
                        + "\n" + "_____________________________________" + "\n"
                        + "\n" + "      1.  Libros"
                        + "\n" + "      2.  Clientes"
                        + "\n" + "      3.  Reservas"
                        + "\n" + "      0.   Salir" + "\n", "SISTEMA DE BIBLIOTECA", JOptionPane.INFORMATION_MESSAGE
            )

        if (seleccion != null) {
            var num = seleccion.toInt()
            when (num) {
                1 -> {menuLibros()}
                2 -> {}
                3 -> {menuReserva()}
                0 -> {
                    JOptionPane.showMessageDialog(null, "Hasta Pronto !!")
                    System.exit(0)
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hasta Pronto !!")
            System.exit(0)
        }
    }
}

fun menuLibros() {
    do {
        val seleccion = JOptionPane.showInputDialog(
            null, "LIBROS" + "\n" + "\n"
                    + "\n" + "Que desea Hacer:" + "\n"
                    + "\n" + "      1.  Ingresar Nuevo Libro"
                    + "\n" + "      2.  Consultar Libro"
                    + "\n" + "      3.  Actualizar Libro"
                    + "\n" + "      4.  Eliminar Libro"
                    + "\n" + "      0.   Salir" + "\n", "SISTEMA DE BIBLIOTECA", JOptionPane.INFORMATION_MESSAGE
        )

        if (seleccion != null) {
            var num = seleccion.toInt()
            when (num) {
                1 -> {menuIngresarLibro()}
                2 -> {menuBuscarLibro() }
                3 -> {menuActualizarLibro()}
                4 -> {menuEliminarLibro()}
                0 -> {
                    JOptionPane.showMessageDialog(null, "Hasta Pronto")
                    System.exit(0)
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hasta Pronto")
            System.exit(0)
        }
    } while (seleccion != null);
}


fun menuReserva() {
    do {
        val seleccion = JOptionPane.showInputDialog(
            null, "Alquiler Libros" + "\n" + "\n"
                    + "\n" + "Que desea Hacer:" + "\n"
                    + "\n" + "      1.  Rentar un Libro"
                    + "\n" + "      2.  Devolver un Libro"
                    + "\n" + "      3.  Ver Libros Disponibles"
                    + "\n" + "      0.   Salir" + "\n", "SISTEMA DE BIBLIOTECA", JOptionPane.INFORMATION_MESSAGE
        )

        if (seleccion != null) {
            var num = seleccion.toInt()
            when (num) {
                1 -> {menuRentarLibro()}
                2 -> {menuDevolverLibro()}
                3 -> {}
                0 -> {
                    JOptionPane.showMessageDialog(null, "Hasta Pronto")
                    System.exit(0)
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hasta Pronto")
            System.exit(0)
        }
    } while (seleccion != null);
}


fun menuRentarLibro(){
    do {
        var opc = 0
        try {

            val cedula = (JOptionPane.showInputDialog("Ingrese su Cédula"))
            val indicePersonaBuscar =buscarPersona(cedula)
            if(indicePersonaBuscar >= 0){
                val codLibro = (JOptionPane.showInputDialog("Codigo del Libro")).toInt()
                val indiceLibroBuscar =buscarLibro(codLibro)
                if (indiceLibroBuscar >= 0){
                    guardarDatosRenta(indicePersonaBuscar,indiceLibroBuscar);
                }else{
                    JOptionPane.showMessageDialog(null,"No existe el Libro")
                }
            }else{
                JOptionPane.showMessageDialog(null,"Usuario No Resgistrado")
            }

            opc = JOptionPane.showConfirmDialog(null, "Desea Rentar otro libro?")
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Se produjo un Error")
            break
        }
    } while (opc == 0)
}

fun menuDevolverLibro(){
    do {
        var opc = 0
        try {

            val cedula = (JOptionPane.showInputDialog("Ingrese su Cédula"))
            val indicePersonaBuscar =buscarPersona(cedula)
            if(indicePersonaBuscar >= 0){
                val codLibro = (JOptionPane.showInputDialog("Codigo del Libro a Devolver")).toInt()
                val indiceLibroBuscar =buscarLibro(codLibro)
                if (indiceLibroBuscar >= 0){
                    devolverLibro(cedula,codLibro);
                }else{
                    JOptionPane.showMessageDialog(null,"No existe el Libro")
                }
            }else{
                JOptionPane.showMessageDialog(null,"Usuario No Resgistrado")
            }

            opc = JOptionPane.showConfirmDialog(null, "Desea Devolver Otro Libro")
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Se produjo un Error")
            break
        }
    } while (opc == 0)
}

fun buscarPersona(cedula:String):Int{

    biblioteca.personas.forEach {
        if(it.codigoP.equals(cedula)){
            JOptionPane.showMessageDialog(null,"Se encontro a la persona")
            val indicePersona = biblioteca.personas.indexOf(it);
            println(indicePersona);
            return indicePersona
        }
    }
    return -1
}

fun guardarDatosRenta(indicePersona:Int,indiceLibro:Int){

    val nuevaReserva = Reserva()
    val ultimoDato = biblioteca.reservas.size - 1
    nuevaReserva.codReserva = biblioteca.reservas[ultimoDato].codReserva + 1

    nuevaReserva.persona = biblioteca.personas[indicePersona]
    nuevaReserva.libro = biblioteca.libros[indiceLibro]
    nuevaReserva.estado="Prestado"
    biblioteca.reservas.add(nuevaReserva)
    actualizarBaseDatos(biblioteca)

    JOptionPane.showMessageDialog(null,"Se Guardo su Pedido")
}

fun devolverLibro(indicePersona:String,indiceLibro:Int){

    val indiceReserva = buscarReservaPorCedulayLibro(indicePersona,indiceLibro)
    biblioteca.reservas[indiceReserva].estado="Devuelto"
    actualizarBaseDatos(biblioteca)
    JOptionPane.showMessageDialog(null,"Gracias")
}

fun filtrarReservasDeUnCliente(cedula:String){

    val respuestaFilter = biblioteca.reservas.filter{
        it.persona.codigoP.equals(cedula)
    }

  respuestaFilter.forEach {
      println(it.libro.nombre)
  }

    val listaReservas=ArrayList<Reserva>()


}

fun buscarReservaPorCedulayLibro(cedula:String,codigoLibro:Int):Int{
    biblioteca.reservas.forEach {
        if(it.persona.codigoP.equals(cedula) && it.libro.codigo == codigoLibro){
            JOptionPane.showMessageDialog(null,"Se encontro la Reserva")
            val indiceReserva = biblioteca.reservas.indexOf(it);
            println(indiceReserva);
            return indiceReserva
        }
    }
    return -1
}

