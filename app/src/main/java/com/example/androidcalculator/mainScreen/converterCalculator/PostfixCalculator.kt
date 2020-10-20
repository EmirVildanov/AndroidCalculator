package com.example.androidcalculator.mainScreen.converterCalculator

import java.util.Stack
import java.util.EmptyStackException

class PostfixCalculator {
    fun calculate(input: String): Double {
        try {
            val stack = Stack<Double>()
            val tokens = input.split(" ")
            for (token in tokens) {
                when {
                    isOperation(token) && stack.size >= 2 -> {
                        val operandA = stack.pop()
                        val operandB = stack.pop()
                        stack.push(doOperation(token, operandA, operandB))
                    }
                    isNumber(token) -> {
                        stack.push(token.toDouble())
                    }
                    else -> {
                        println("Incorrect input is : '$token'")
                        throw IllegalStateException("Incorrect input")
                    }
                }
            }
            return stack.pop()
        } catch (exception: EmptyStackException) {
            throw exception
        }
    }

    private fun isOperation(input: String): Boolean {
        return input == "+" || input == "-" || input == "*" || input == "/"
    }

    private fun isNumber(input: String): Boolean {
        return input.matches(Regex("[0-9]+(.[0-9]+)?"))
    }

    private fun doOperation(operation: String, firstNumber: Double, secondNumber: Double): Double {
        var answer = 0.0
        if (operation == "+") {
            answer = firstNumber + secondNumber
        } else if (operation == "-") {
            answer = secondNumber - firstNumber
        } else if (operation == "*") {
            answer = firstNumber * secondNumber
        } else if (operation == "/") {
            answer = secondNumber / firstNumber
        }
        return answer
    }
}
