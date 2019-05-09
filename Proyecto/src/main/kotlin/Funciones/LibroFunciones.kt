package Funciones

import Clases.Libro
import javax.swing.JOptionPane


fun menuIngresarLibro() {
    do {
        var opc = 0
        try {

            val libro = Libro()
            libro.codigo = (JOptionPane.showInputDialog("Codigo del Libro")).toInt()
            libro.nombre = JOptionPane.showInputDialog("Nombre del Libro")
            insertarLibro(libro)
            opc = JOptionPane.showConfirmDialog(null, "Desea Ingresar otro Libro?")
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Se produjo un Error")
            break
        }
    } while (opc == 0)
}

fun menuBuscarLibro() {
    do {
        var opc = 0
        try {

            val codigo = (JOptionPane.showInputDialog("Codigo del Libro a Buscar")).toInt()
            val indiceLibroABuscar = buscarLibro(codigo)
            if (indiceLibroABuscar != -1) {
                JOptionPane.showMessageDialog(
                    null, "Libro Encontrado " + "\n" +
                            "Codigo:" + biblioteca.libros[indiceLibroABuscar].codigo + "\n" +
                            "Nombre:" + biblioteca.libros[indiceLibroABuscar].nombre + "\n"
                )
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el Libro")
            }
            opc = JOptionPane.showConfirmDialog(null, "Desea Buscar otro Libro?")
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Se produjo un Error")
            break
        }
    } while (opc == 0)
}

fun menuActualizarLibro() {
    do {
        var opc = 0
        try {

            val codigo = (JOptionPane.showInputDialog("Codigo del Libro a Actualizar")).toInt()
            val indiceLibroABuscar = buscarLibro(codigo)
            if (indiceLibroABuscar != -1) {
                val libro = Libro()
//                libro.codigo = (JOptionPane.showInputDialog("Nuevo Codigo")).toInt()
                libro.codigo = codigo
                libro.nombre = JOptionPane.showInputDialog("Nuevo Nombre")
                actualizarLibro(libro)
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el Libro")
            }
            opc = JOptionPane.showConfirmDialog(null, "Desea Buscar otro Libro?")
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Se produjo un Error")
            break
        }
    } while (opc == 0)

}

fun menuEliminarLibro() {
    do {
        var opc = 0
        try {

            val codigo = (JOptionPane.showInputDialog("Codigo del Libro a Eliminar")).toInt()
            val indiceLibroAEliminar = buscarLibro(codigo)
            if (indiceLibroAEliminar != -1) {
                borrarLibro(indiceLibroAEliminar)
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el Libro")
            }
            opc = JOptionPane.showConfirmDialog(null, "Desea Buscar otro Libro?")
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Se produjo un Error")
            break
        }
    } while (opc == 0)

}


fun insertarLibro(libro: Libro) {

    if (biblioteca.libros.add(libro)) {
        actualizarBaseDatos(biblioteca);
        JOptionPane.showMessageDialog(null, "Se Ingreso Correctamente")
    } else {
        JOptionPane.showMessageDialog(null, "Error en el Ingreso")
    }


}

fun borrarLibro(indice: Int) {

    if (biblioteca.libros.removeAt(indice) != null) {
        JOptionPane.showMessageDialog(
            null,
            "Libro Eliminado"
        )
        actualizarBaseDatos(biblioteca)
    } else {
        JOptionPane.showMessageDialog(
            null,
            "Error al eliminar"
        )
    }
}

fun buscarLibro(codigo: Int): Int {

    biblioteca.libros.forEach({
        if (it.codigo == codigo) {
            return biblioteca.libros.indexOf(it)
        }
    }
    )
    return -1;
}

fun actualizarLibro(libro: Libro) {

    val indice = buscarLibro(libro.codigo)
    if (indice >= 0) {
        biblioteca.libros[indice].nombre = libro.nombre;
        actualizarBaseDatos(biblioteca)
        JOptionPane.showMessageDialog(null, "Libro Actualizado Con exito")
    } else {
        JOptionPane.showMessageDialog(null, "No se puede actualizar")
    }
}