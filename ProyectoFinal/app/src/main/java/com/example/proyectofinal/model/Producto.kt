package com.example.proyectofinal.model

class Producto(var id: Int, var nombre: String, var precio: String,
               var descripcion: String, var descuento: Double, var rating: Double, var stock: Int,
               var marca: String, var categoria: String, var imagen: String){

    constructor( id: Int, nombre: String, precio: String,  descripcion: String,
                descuento: Double, rating: Double, stock: Int, marca: String, categoria: String, imagen: String, adincional: String
                )
            : this(id, nombre, precio, descripcion, descuento, rating, stock, marca, categoria, imagen) {}
}