package ca.georgiancollege.assignment2

import android.util.Log
import android.widget.TextView
import ca.georgiancollege.assignment2.databinding.ActivityMainBinding

class Calculation (databinding: ActivityMainBinding){
    private var binding: ActivityMainBinding = databinding
    init {
        buttonsCall()
    }
    private fun buttonsCall() {
        val operands = arrayOf(
            binding.Zero,
            binding.One,
            binding.Two,
            binding.Three,
            binding.Four,
            binding.Five,
            binding.Six,
            binding.Seven,
            binding.Eight,
            binding.Nine,
            binding.decimal
        )
        for (operand in operands) {
            operand.setOnClickListener {
                numbersSharedHandler(operand.tag.toString())
            }
        }
        val symbols = arrayOf(
            binding.Plus,
            binding.Minus,
            binding.Multiply,
            binding.divide,
            binding.percent,
            binding.EqualsTo,
            binding.backspace,
            binding.AllClear,
            binding.PlusMinus
        )
        for (symbol in symbols) {
            if (symbol != null) {
                symbol.setOnClickListener {
                    symbolsSharedHandler(symbol.tag.toString())
                }
            }
        }
    }

    private fun numbersSharedHandler(numbersTag : String)
    {
        val calculationsTextView: TextView = binding.CalculationsView
        val resultTextView: TextView = binding.Result
        if(resultTextView.text == "0"){
            when(numbersTag)
            {
                "0"-> resultTextView.text = "0"
                "."-> resultTextView.text = "0."
                else -> resultTextView.text = numbersTag
            }
        }
        else{
            when(numbersTag)
            {
                "."->{
                    if(!resultTextView.text.toString().contains(".")){
                        resultTextView.text = resultTextView.text.toString() + numbersTag
                    }
                }
                else -> {
                    resultTextView.text = resultTextView.text.toString() + numbersTag
                }
            }
        }

    }

    private fun symbolsSharedHandler(symbolTag: String)
    {
        val calculationsTextView: TextView = binding.CalculationsView
        val resultTextView: TextView = binding.Result
        when(symbolTag)
        {
            "AllClear" -> {
                calculationsTextView.text = "0"
                resultTextView.text = "0"
            }
            "Backspace" -> {
                if(resultTextView.text.length > 1 ){
                    if (resultTextView.text.toString().length <= 2 && (resultTextView.text[0]=='-' ||resultTextView.text[0]=='.')){
                        resultTextView.text = resultTextView.text.toString().dropLast(2)
                        resultTextView.text = "0"
                    }else{
                        resultTextView.text = resultTextView.text.toString().dropLast(1)
                    }
                }else if (resultTextView.text.length == 1){
                    resultTextView.text = "0"
                }

            }
            "PlusMinus" ->{
                if(resultTextView.text.contains(".")){
                    resultTextView.text = (resultTextView.text.toString().toDouble() * -1).toString()
                }else{
                    resultTextView.text = (resultTextView.text.toString().toInt() * -1).toString()
                }

            }
            "Plus" ->{
                if(calculationsTextView.text.toString() == "0"){
                    calculationsTextView.text = resultTextView.text.toString() + "+"
                    resultTextView.text = "0"
                }else{
                    calculationsTextView.text = calculationsTextView.text.toString() + resultTextView.text.toString() + "+"
                    resultTextView.text = "0"
                }
//                calculationsTextView.text = calculationsTextView.text.toString() + "+"

            }
            "Minus" ->{
                if (calculationsTextView.text.toString() == "0"){
                    calculationsTextView.text = resultTextView.text.toString() + "-"
                    resultTextView.text = "0"
                }else{
                    calculationsTextView.text = calculationsTextView.text.toString() + resultTextView.text.toString() + "-"
                    resultTextView.text = "0"
                }
            }
            "Multiply" ->{
                if (calculationsTextView.text.toString() == "0"){
                    calculationsTextView.text = resultTextView.text.toString() + "X"
                    resultTextView.text = "0"
                }else{
                    calculationsTextView.text = calculationsTextView.text.toString() + resultTextView.text.toString() + "X"
                    resultTextView.text = "0"
                }
            }
            "Divide" ->{
                if (calculationsTextView.text.toString() == "0"){
                    calculationsTextView.text = resultTextView.text.toString() + (binding.divide?.text ?:"/")
                    resultTextView.text = "0"
                }else{
                    calculationsTextView.text = calculationsTextView.text.toString() + resultTextView.text.toString() + (binding.divide?.text ?:"/")
                    resultTextView.text = "0"
                }
            }
            "Percent" ->{
                if (calculationsTextView.text.toString() == "0"){
                    calculationsTextView.text = resultTextView.text.toString() + "%"
                    resultTextView.text = "0"
                }else{
                    calculationsTextView.text = calculationsTextView.text.toString() + resultTextView.text.toString() + "%"
                    resultTextView.text = "0"
                }
            }
            "Equals" -> {
                val operators = arrayOf('+', '-', '*', '/', '%')
                if (calculationsTextView.text.toString() == "0"){
                    calculationsTextView.text = resultTextView.text.toString()
                    resultTextView.text = resultTextView.text.toString()
                }else if (operators.contains(calculationsTextView.text.last())){
                    if (resultTextView.text.toString() == "0"){
                        calculationsTextView.text = calculationsTextView.text.toString().dropLast(1)
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + resultTextView.text.toString()
                    }
                }
                val calculations = calculationsTextView.text.toString()
                val result = calculate(calculations)
                resultTextView.text = result.toString()
            }
            else -> calculationsTextView.text = calculationsTextView.text.toString()
        }
    }

    private fun calculate(calculations: String): Any {
        val operatorsPrecedence = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2, '%' to 3)
        val operators = operatorsPrecedence.keys
        return 0.0
    }

    private fun addition(firstNum: Double, secondNum: Double): Double {
        return firstNum + secondNum
    }
    private fun subtraction(firstNum: Double, secondNum: Double): Double {
        return firstNum - secondNum
    }
    private fun multiplication(firstNum: Double, secondNum: Double): Double {
        return firstNum * secondNum
    }
    private fun division(firstNum: Double, secondNum: Double): Double {
        return firstNum / secondNum
    }
    private fun percentage(firstNum: Double, secondNum: Double): Double {
        return (firstNum * secondNum) / 100
    }
}