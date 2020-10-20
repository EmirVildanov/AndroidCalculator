package com.example.androidcalculator

import com.example.androidcalculator.mainScreen.converterCalculator.PostfixCalculator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PostfixCalculatorTest {
    private val calculator = PostfixCalculator()
    @Test
    fun shouldCalculateSimpleInput() {
        Assertions.assertEquals(168.0, calculator.calculate("56 3 *"))
    }

    @Test
    fun shouldCalculateInputWithTwoOperations() {
        Assertions.assertEquals(-192.0, calculator.calculate("11 5 - 32 *"))
    }

    @Test
    fun shouldCalculateInputWithRealNumbers() {
        Assertions.assertEquals(12.0, calculator.calculate("1.5 1 * 4.5 3 * -"))
    }

    @Test
    fun shouldCalculateInputWithRealNumbersIntoRealNumber() {
        Assertions.assertEquals(1.5, calculator.calculate("1.5 1 *"))
    }
}
