package com.example.apprestauranteluisaguilera.model

data class Platillo(
    val nombre: String,
    val precio: Int,
    var cantidad: Int = 0
)
