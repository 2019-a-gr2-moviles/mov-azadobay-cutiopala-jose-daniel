package com.example.proyecto_biblioteca.Solicitudes

import com.example.proyecto_biblioteca.Lectores.ClienteAuxiliar
import com.example.proyecto_biblioteca.Libros.LibroAuxiliar

class Solicitud (
    var id: Int?,
    var fecha: String?,
    var validez: Boolean?,
    var codigoCli: ClienteAuxiliar?,
    var codigoZap: LibroAuxiliar?
) {
}