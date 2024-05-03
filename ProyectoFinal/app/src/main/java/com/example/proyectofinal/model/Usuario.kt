package com.example.proyectofinal.model

class Usuario(
    var nombre: String? = null,
    var correo: String? = null,
    var edad: Int? = null,
    var direccion: String? = null
) {
    override fun toString(): String {
        return "Usuario(nombre=$nombre, correo=$correo, edad=$edad, direccion=$direccion)"
    }
}