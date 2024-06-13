package ca.georgiancollege.assignment2

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
        if(calculationsTextView.text == "0"){
            when(numbersTag)
            {
                "0"-> calculationsTextView.text = "0"
                "."-> calculationsTextView.text = "0."
                else -> calculationsTextView.text = numbersTag
            }
        }
        else{
            when(numbersTag)
            {
                "."->{
                    if(!calculationsTextView.text.toString().contains(".")){
                        calculationsTextView.text = calculationsTextView.text.toString() + numbersTag
                    }
                }
                else -> {
                    calculationsTextView.text = calculationsTextView.text.toString() + numbersTag
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
                if(calculationsTextView.text.length > 1 ){
                    if (calculationsTextView.text.toString().length <= 2 && (calculationsTextView.text[0]=='-' ||calculationsTextView.text[0]=='.')){
                        calculationsTextView.text = calculationsTextView.text.toString().dropLast(2)
                        calculationsTextView.text = "0"
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString().dropLast(1)
                    }
                }else if (calculationsTextView.text.length == 1){
                    calculationsTextView.text = "0"
                }

            }
            "PlusMinus" ->{
                if(calculationsTextView.text.contains(".")){
                    calculationsTextView.text = (calculationsTextView.text.toString().toDouble() * -1).toString()
                }else{
                    calculationsTextView.text = (calculationsTextView.text.toString().toInt() * -1).toString()
                }

            }
            "Plus" ->{
                calculationsTextView.text = calculationsTextView.text.toString() + "+"

            }
            "Minus" ->{
                calculationsTextView.text = calculationsTextView.text.toString() + "-"
            }
            "Multiply" ->{
                calculationsTextView.text = calculationsTextView.text.toString() + "X"
            }
            "Divide" ->{
                calculationsTextView.text = calculationsTextView.text.toString() + (binding.divide?.text ?:"/")
            }
            "Percent" ->{
                calculationsTextView.text = calculationsTextView.text.toString() + "%"
            }
            "Equals" -> {
            }
            else -> calculationsTextView.text = calculationsTextView.text.toString()
        }
    }
}