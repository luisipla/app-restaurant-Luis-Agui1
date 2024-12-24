package com.example.apprestauranteluisaguilera

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.apprestauranteluisaguilera.model.Pedido
import com.example.apprestauranteluisaguilera.model.Platillo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etCantidad1 = findViewById<EditText>(R.id.etCantidad1)
        val etCantidad2 = findViewById<EditText>(R.id.etCantidad2)
        val tvComida = findViewById<TextView>(R.id.tvComida)
        val tvPropina = findViewById<TextView>(R.id.tvPropina)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val switchPropina = findViewById<Switch>(R.id.switchPropina)

        val pastelDeChoclo = Platillo("Pastel de Choclo", 12000)
        val cazuela = Platillo("Cazuela", 10000)
        val pedido = Pedido()
        pedido.platillos.addAll(listOf(pastelDeChoclo, cazuela))

        etCantidad1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                pastelDeChoclo.cantidad = s.toString().toIntOrNull() ?: 0
                actualizarTotales(pedido, tvComida, tvPropina, tvTotal)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etCantidad2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                cazuela.cantidad = s.toString().toIntOrNull() ?: 0
                actualizarTotales(pedido, tvComida, tvPropina, tvTotal)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            pedido.incluirPropina = isChecked
            actualizarTotales(pedido, tvComida, tvPropina, tvTotal)
        }
    }

    private fun actualizarTotales(pedido: Pedido, tvComida: TextView, tvPropina: TextView, tvTotal: TextView) {
        val subtotal = pedido.calcularSubtotal()
        val propina = pedido.calcularPropina()
        val total = pedido.calcularTotal()

        tvComida.text = "Comida: $$subtotal"
        tvPropina.text = "Propina: $$propina"
        tvTotal.text = "TOTAL: $$total"
    }
}
