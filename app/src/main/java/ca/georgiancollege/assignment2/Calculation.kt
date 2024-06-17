package ca.georgiancollege.assignment2

import android.util.Log
import android.widget.TextView
import ca.georgiancollege.assignment2.databinding.ActivityMainBinding
import kotlin.math.cbrt
import kotlin.math.pow
import kotlin.math.sqrt

class Calculation (databinding: ActivityMainBinding){
    private var binding: ActivityMainBinding = databinding
    private var currentExpression: String = ""

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
            binding.decimal,
            binding.pi
        )
        for (operand in operands) {
            if (operand != null) {
                operand.setOnClickListener {
                    numbersSharedHandler(operand.tag.toString())
                }
            }
        }
        val symbols = arrayOf(
            binding.Plus,
            binding.Minus,
            binding.Multiply,
            binding.Divide,
            binding.percent,
            binding.EqualsTo,
            binding.backspace,
            binding.AllClear,
            binding.PlusMinus,
            binding.squareRoot,
            binding.cubeRoot,
            binding.square,
            binding.cube,
            binding.Factorial,
            binding.LeftBracket,
            binding.RightBracket
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
            "%" -> {
                if (calculationsTextView.text.toString() == "0"){
                    var percentageResult =percentage(resultTextView.text.toString().toDouble(),1.0)
                    calculationsTextView.text = "0"
                    resultTextView.text = percentageResult.toString()
                }
                else{
                    var expression = calculationsTextView.text.toString()
                    var operands = expression.split("+", "-", "X", "/")
                    var lastOperator = expression.last()
                    operands = operands.dropLast(1)
                    Log.i("Last Operand", resultTextView.text.toString())
                    Log.i("Last Operand", operands.last())
                    var percentageResult = percentage(resultTextView.text.toString().toDouble(), 1.0)
                    calculationsTextView.text = calculationsTextView.text.toString() + percentageResult.toString()
                    resultTextView.text = ""
                }
            }
            "Equals" -> {
                val operators = arrayOf('+', '-', 'X', '/', '%')
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
                resultTextView.text = result.toString().format("%.6f")
                calculationsTextView.text = "0"
            }
            "^2"->{
                if (resultTextView.text.toString().contains(".")){
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =square(resultTextView.text.toString().toDouble()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + symbolTag + resultTextView.text.toString()
                        resultTextView.text = ""
                    }
                }else{
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =square(resultTextView.text.toString().toInt()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + square(resultTextView.text.toString().toInt()).toString()
                        resultTextView.text = ""
                    }
                }
            }
            "^3"->{
                if (resultTextView.text.toString().contains(".")){
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =cube(resultTextView.text.toString().toDouble()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + symbolTag + resultTextView.text.toString()
                        resultTextView.text = ""
                    }
                }else{
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =cube(resultTextView.text.toString().toInt()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + cube(resultTextView.text.toString().toInt()).toString()
                        resultTextView.text = ""
                    }
                }
            }
            "!"->{
                if (resultTextView.text.toString().contains(".")){
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =factorial(resultTextView.text.toString().toDouble()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + factorial(resultTextView.text.toString().toDouble()).toString()
                        resultTextView.text = ""
                    }
                }else{
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =factorial(resultTextView.text.toString().toInt()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + factorial(resultTextView.text.toString().toInt()).toString()
                        resultTextView.text = ""
                    }
                }
            }
            "@string/SquareRootIcon"->{
                if (resultTextView.text.toString().contains(".")){
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =squareRoot(resultTextView.text.toString().toDouble()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + squareRoot(resultTextView.text.toString().toDouble()).toString()
                        resultTextView.text = ""
                    }
                }else{
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =squareRoot(resultTextView.text.toString().toInt()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + squareRoot(resultTextView.text.toString().toInt()).toString()
                        resultTextView.text = ""
                    }
                }
            }
            "@string/CubeRootIcon"->{
                if (resultTextView.text.toString().contains(".")){
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =cubeRoot(resultTextView.text.toString().toDouble()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + cubeRoot(resultTextView.text.toString().toDouble()).toString()
                        resultTextView.text = ""
                    }
                }else{
                    if (calculationsTextView.text.toString() == "0") {
                        resultTextView.text =cubeRoot(resultTextView.text.toString().toInt()).toString()
                    }else{
                        calculationsTextView.text = calculationsTextView.text.toString() + cubeRoot(resultTextView.text.toString().toInt()).toString()
                        resultTextView.text = ""
                    }
                }
            }
            "1 / x"->{
                if(!(resultTextView.text.toString() == "0" || resultTextView.text.toString() == "0.0" || resultTextView.text.toString() == " ")){
                    if (resultTextView.text.toString().contains(".")) {
                        if (calculationsTextView.text.toString() == "0") {
                            resultTextView.text =
                                (1 / resultTextView.text.toString().toDouble()).toString()
                        } else {
                            calculationsTextView.text =
                                calculationsTextView.text.toString() + (1 / resultTextView.text.toString().toDouble()).toString()
                            resultTextView.text = ""
                        }
                    } else {
                        if (calculationsTextView.text.toString() == "0") {
                            resultTextView.text =
                                (1 / resultTextView.text.toString().toInt()).toString()
                        } else {
                            calculationsTextView.text =
                                calculationsTextView.text.toString() + (1 / resultTextView.text.toString().toInt()).toString()
                            resultTextView.text = ""
                        }
                    }
                }else{
                    resultTextView.text = "Error"
                }
            }
            "^"->{
                if (calculationsTextView.text.toString() == "0"){
                    calculationsTextView.text = resultTextView.text.toString() + "^"
                    resultTextView.text = "0"
                }else{
                    calculationsTextView.text = calculationsTextView.text.toString() + resultTextView.text.toString() + "^"
                    resultTextView.text = "0"
                }
            }
            else -> {
                if (calculationsTextView.text.toString() == "0"){
                    calculationsTextView.text = resultTextView.text.toString() + symbolTag
                    resultTextView.text = "0"
                }else{
                    calculationsTextView.text = calculationsTextView.text.toString() + resultTextView.text.toString() + symbolTag
                    resultTextView.text = "0"
                }
            }
        }
    }

    private fun calculate(calculations: String): Any {
        val operatorsPrecedence = mapOf('+' to 4, '-' to 4, 'X' to 3, '/' to 3, '%' to 2,'^' to 1)
        var result :Any = 0
        val operators = ArrayList<Char>()
        val operands = ArrayList<Double>()
        var operand = ""
        calculations.forEach { char ->
            if (char.isDigit() || char == '.' || (char == '-' && operand.isEmpty())) {
                operand += char
            }else if (char =='+' || char == '-' || char == 'X' || char == '/' || char == '%' || char == '^') {
                operands.add(operand.toDouble())
                operand = ""
                operators.add(char)
            }
        }
        operands.add(operand.toDouble())
        val operatorsSorted = operators.sortedBy { operatorsPrecedence[it] }
        Log.i("Operators", operatorsSorted.toString())
        for (operator in operatorsSorted) {
            val operatorIndex = operators.indexOf(operator)
            var firstNum = operands[operatorIndex]
            var secondNum = operands[operatorIndex + 1]
            when (operator) {
                '+' -> {
                    result = addition(firstNum, secondNum)
                }
                '-' -> {
                    result = subtraction(firstNum, secondNum)
                }
                'X' -> {
                    result = multiplication(firstNum, secondNum)
                }
                '/' -> {
                    if (secondNum == 0.0 || secondNum.toInt() == 0) {
                        result = "Error"
                    } else {
                        result = division(firstNum, secondNum)
                    }
                }
                '^' -> {
                    result = power(firstNum, secondNum)
                }
            }
            operands.removeAt(operatorIndex)
            operands.removeAt(operatorIndex)
            operands.add(operatorIndex, result as Double)
            operators.removeAt(operatorIndex)
        }
        return result
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
        return firstNum * (secondNum/ 100)
    }
    private fun cubeRoot(firstNum: Double): Double {
        return cbrt(firstNum)
    }
    private fun cubeRoot(firstNum: Int): Int{
        return cbrt(firstNum.toDouble()).toInt()
    }
    private fun squareRoot(firstNum: Double): Double {
        return sqrt(firstNum)
    }
    private fun squareRoot(firstNum: Int): Int{
        return sqrt(firstNum.toDouble()).toInt()
    }
    private fun square(firstNum: Double): Double {
        return firstNum * firstNum
    }
    private fun square(firstNum: Int): Int{
        return firstNum * firstNum
    }
    private fun cube(firstNum: Double): Double {
        return firstNum * firstNum * firstNum
    }
    private fun cube(firstNum: Int): Int{
        return firstNum * firstNum * firstNum
    }
    private fun factorial(firstNum: Double): Double {
        var result = 1.0
        for (i in 1..firstNum.toInt()) {
            result *= i
        }
        return result
    }
    private fun factorial(firstNum: Int): Int {
        var result = 1
        for (i in 1..firstNum.toInt()) {
            result *= i
        }
        return result
    }
    private fun power(firstNum: Double, secondNum: Double): Double {
        return firstNum.pow(secondNum)
    }
}