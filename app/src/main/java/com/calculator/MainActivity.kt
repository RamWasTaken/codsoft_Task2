package com.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var equationTextView: TextView
    private lateinit var resultTextView: TextView

    private var currentInput = StringBuilder()
    private var currentOperator: Char? = null
    private var operand1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        equationTextView = findViewById(R.id.equation)
        resultTextView = findViewById(R.id.result_textView)
    }

    fun onNumberClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()
        currentInput.append(buttonText)
        equationTextView.append(buttonText)
    }

    fun onOperatorClick(view: View) {
        if (currentOperator == null) {
            val operatorButton = view as Button
            currentOperator = operatorButton.text[0]
            operand1 = currentInput.toString().toDouble()
            equationTextView.append(currentOperator.toString())
            currentInput.clear()
        }
    }

    fun onClearClick(view: View) {
        currentInput.clear()
        currentOperator = null
        operand1 = null
        equationTextView.text = ""
        resultTextView.text = "0"
    }

    fun onEqualClick(view: View) {
        if (currentOperator != null) {
            val operand2 = currentInput.toString().toDouble()
            val result = when (currentOperator) {
                '+' -> operand1!! + operand2
                '-' -> operand1!! - operand2
                '*' -> operand1!! * operand2
                '/' -> {
                    if (operand2 != 0.0) {
                        operand1!! / operand2
                    } else {
                        "Error"
                    }
                }
                else -> 0.0
            }
            resultTextView.text = result.toString()
            currentInput.clear()
            currentOperator = null
            equationTextView.text = result.toString()
        }
    }
}
