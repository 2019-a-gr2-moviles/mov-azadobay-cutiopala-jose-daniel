package com.example.proyecto_biblioteca.Libros

class Libro (var createdAt: Long?,
             var updatedAt: Long?,
             var id: Int?,
             var titulo: String,
             var autor: String,
             var numeroEdicion: String,
             var editorial: String,
             var stock: Int){
}