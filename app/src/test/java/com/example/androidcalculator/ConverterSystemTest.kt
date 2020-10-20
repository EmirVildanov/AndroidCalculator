package com.example.androidcalculator

import com.example.androidcalculator.mainScreen.converterCalculator.InfixConverter
import com.example.androidcalculator.mainScreen.converterCalculator.PostfixCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConverterSystemTest {
    private val converter = InfixConverter()
    private val calculator = PostfixCalculator()
    @Test
    fun shouldCalculateInfixLine() {
        assertEquals(8.0, calculator.calculate(converter.convert("( 3 + 5 )")))
    }

    @Test
    fun shouldCalculateInfixLineWithBracesAndOneValue() {
        assertEquals(9.0, calculator.calculate(converter.convert("3 * ( 5 - 2 )")))
    }

    @Test
    fun shouldCheckDividing() {
        assertEquals(4.0, calculator.calculate(converter.convert("8 / 2")))
    }

    @Test
    fun shouldCheckSubtracting() {
        assertEquals(6.0, calculator.calculate(converter.convert("8 - 2")))
    }
}
