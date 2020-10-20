package com.example.androidcalculator.mainScreen.converterCalculator

import java.util.Stack
import java.util.Queue
import java.util.LinkedList

class InfixConverter {
    private val stack = Stack<String>()
    private val queue: Queue<String> = LinkedList<String>()

    fun convert(input: String): String {
        val tokens = input.split(" ")
        for (token in tokens) {
            readToken(token)
        }
        while (stack.isNotEmpty()) {
            val poppingValue = stack.pop()
            if (poppingValue == "(" || poppingValue == ")") {
                throw InfixConversionException("Check braces. Input is incorrect")
            }
            queue.add(poppingValue)
        }
        var answerString = String()
        while (queue.isNotEmpty()) {
            answerString += "${queue.poll()} "
        }
        answerString = answerString.dropLast(1)
        return answerString
    }

    private fun readToken(token: String) {
        when {
            isNumber(token) -> {
                queue.add(token)
            }
            token == "(" -> stack.push(token)
            token == ")" -> {
                while (!stack.isEmpty() && stack.peek() != "(") {
                    queue.add(stack.pop())
                }
                if (stack.isEmpty()) {
                    throw InfixConversionException("The opening bracket is missed")
                }
                stack.pop()
            }
            isOperation(token) -> {
                while (stack.isNotEmpty() && stack.peek() != "(" &&
                    getPriority(stack.peek()) >= getPriority(token)) {
                    queue.add(stack.pop())
                }
                stack.push(token)
            }
            else -> {
                throw InfixConversionException("Unknown token")
            }
        }
        return
    }

    private fun isNumber(input: String): Boolean {
        return input.matches(Regex("[0-9]+(.[0-9]+)?"))
    }

    private fun isOperation(input: String): Boolean {
        return input == "+" || input == "-" || input == "*" || input == "/"
    }

    private fun getPriority(operator: String): Int {
        return if (operator == "+" || operator == "-") {
            1
        } else if (operator == "*" || operator == "/") {
            2
        } else {
            throw InfixConversionException("Problem with braces")
        }
    }
}
