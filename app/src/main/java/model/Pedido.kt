package com.example.apprestauranteluisaguilera.model

class Pedido {
    val platillos: MutableList<Platillo> = mutableListOf()
    var incluirPropina: Boolean = false

    fun calcularSubtotal(): Int {
        return platillos.sumBy { it.precio * it.cantidad }
    }

    fun calcularPropina(): Int {
        return if (incluirPropina) (calcularSubtotal() * 0.1).toInt() else 0
    }

    fun calcularTotal(): Int {
        return calcularSubtotal() + calcularPropina()
    }
}
