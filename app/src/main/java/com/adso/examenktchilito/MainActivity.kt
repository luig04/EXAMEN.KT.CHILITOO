package com.adso.examenktchilito


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adso.examenktchilito.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private var firstValue= ""
    private var num1= ""
    private var num2= ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        val buttons = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9,
            binding.btnsuma, binding.btnres, binding.btnmulti, binding.btndiv,
            binding.btnClearAll, binding.btnigual, binding.btnDelete, binding.btnpunto
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                handleButtonClick(button.text.toString())
            }
        }
    }

    private fun handleButtonClick(value: String) {
        when {
            value in listOf("+", "-", "*", "/") -> {
                operator = value
                firstValue = binding.inputText.text.toString()
                binding.inputText.text = ""
            }
            value == "=" -> {
                val secondValue = binding.toString()
                try {
                    val result = calculateResult(firstValue.toDouble(), secondValue.toDouble(), operator)
                    binding.inputText.text = result.toString()
                } catch (e: NumberFormatException) {
                    binding.inputText.text = "Error"
                } catch (e: ArithmeticException) {
                    binding.inputText.text = "Error"
                }
                var num1= ""
                var num2= ""
                firstValue = ""
                operator = ""
            }

            else -> {
                binding.inputText.append(value)
            }
        }
    }

    private fun calculateResult(first: Double, second: Double, operator: String): Double {
        return when (operator) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> first / second
            else -> throw IllegalArgumentException("Operador inválido: $operator")
        }
    }
}
