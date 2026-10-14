package com.example.calculadoraapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 1. Referencias a las vistas del XML
    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var Resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. Vincular vistas por ID
        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        Resultado = findViewById(R.id.Resultado)

        // 3. Un listener por botón; todos llaman a calculate(op)
        findViewById<Button>(R.id.btnAdd).setOnClickListener { calculate("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { calculate("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { calculate("×") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { calculate("÷") }
    }

    private fun calculate(op: String) {
        // 1. Texto -> Double (null si está vacío o no es número)
        val n1 = etNum1.text.toString().toDoubleOrNull()
        val n2 = etNum2.text.toString().toDoubleOrNull()

        // 2. Validar que ambos sean números
        if (n1 == null || n2 == null) {
            Resultado.text = "Ingrese números válidos en ambos campos"
            return
        }

        // 3. Ejecutar la operación (when = el "switch" de Kotlin)
        val result = when (op) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "×" -> n1 * n2
            "÷" -> {
                // 4. Validar división por cero
                if (n2 == 0.0) {
                    Resultado.text = "No se puede dividir por 0"
                    return
                }
                n1 / n2
            }
            else -> 0.0
        }

        // 5. Mostrar el resultado (interpolación de string)
        Resultado.text = "Resultado: $result"
    }
}