package com.example.androidcalculator

import com.example.androidcalculator.mainScreen.converterCalculator.InfixConverter
import com.example.calculator.mainScreen.converterCalculator.InfixConversionException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class InfixConverterTest {
    private val converter = InfixConverter()
    @Test
    fun shouldConvertSimpleInfixInput() {
        assertEquals("56 3 *", converter.convert("56 * 3"))
    }
    @Test
    fun shouldConvertInputWithOnePairOfBraces() {
        assertEquals("11 5 - 32 *", converter.convert("( 11 - 5 ) * 32"))
    }
    @Test
    fun shouldThrowExceptionBecauseOfWrongCloseBrace() {
        assertThrows(InfixConversionException::class.java) {
            converter.convert("( 11 - 5 * 32")
        }
    }
    @Test
    fun shouldThrowExceptionBecauseOfWrongOpenBrace() {
        assertThrows(InfixConversionException::class.java) {
            converter.convert(" 11 - 5 * ( 3 - 32) )")
        }
    }
    @Test
    fun shouldConvertInputWithRealNumber() {
        assertEquals("1.5 1 * 4.5 3 * -", converter.convert("1.5 * 1 - ( 4.5 * 3 )"))
    }
    @Test
    fun test() {
        assertEquals("1.5 1 * 4.5 3 * -", converter.convert("1.5 * 1 - ( 4.5 * 3 )"))
    }
}
