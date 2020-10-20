package com.example.androidcalculator.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidcalculator.mainScreen.converterCalculator.InfixConversionException
import com.example.androidcalculator.mainScreen.converterCalculator.PostfixCalculator
import com.example.androidcalculator.mainScreen.converterCalculator.InfixConverter
import java.util.EmptyStackException

class CalculatorViewModel : ViewModel() {

    enum class CancelButtonState(val stateString: String) {
        DELETE("DEL"),
        CANCEL("C")
    }
    companion object {
        const val OPERATION_STRING_PLACE = 3
    }

    private val _isWrongInput = MutableLiveData<Boolean>(false)
    val isWrongInput: LiveData<Boolean>
        get() = _isWrongInput

    private val _cancelButtonState = MutableLiveData<String>(CancelButtonState.CANCEL.stateString)
    val cancelButtonState: LiveData<String>
        get() = _cancelButtonState

    private val _calculatorInput = MutableLiveData<String>(" ")
    val calculatorInput: LiveData<String>
        get() = _calculatorInput

    fun enterNumberSymbol(symbol: String) {
        if (_cancelButtonState.value == CancelButtonState.DELETE.stateString) {
            _cancelButtonState.value = CancelButtonState.CANCEL.stateString
        }
        _calculatorInput.value += symbol
    }
    fun enterOperationSymbol(symbol: String) {
        if (_cancelButtonState.value == CancelButtonState.DELETE.stateString) {
            _cancelButtonState.value = CancelButtonState.CANCEL.stateString
        }
        if (_calculatorInput.value != "" && _calculatorInput.value?.last() == ' ') {
            _calculatorInput.value += "$symbol "
        } else {
            _calculatorInput.value += " $symbol "
        }
    }
    fun confirmInput() {
        val converter = InfixConverter()
        val calculator = PostfixCalculator()
        lateinit var postfixLine: String
        try {
            postfixLine = converter.convert(_calculatorInput.value!!.trim())
            val value = calculator.calculate(postfixLine)
            _calculatorInput.value = value.toString().removeSuffix(".0")
            _cancelButtonState.value = CancelButtonState.DELETE.stateString
        } catch (exception: InfixConversionException) {
            _isWrongInput.value = true
            return
        } catch (exception: EmptyStackException) {
            _isWrongInput.value = true
        } catch (exception: IllegalStateException) {
            _isWrongInput.value = true
        }
    }

    fun onWrongInputErrorDisplayed() {
        _isWrongInput.value = false
    }

    fun cancelInput() {
        when (cancelButtonState.value) {
            CancelButtonState.DELETE.stateString -> {
                _calculatorInput.value = " "
                _cancelButtonState.value = CancelButtonState.CANCEL.stateString
            }
            CancelButtonState.CANCEL.stateString -> {
                if (_calculatorInput.value != "" && _calculatorInput.value?.last() == ' ') {
                    _calculatorInput.value = _calculatorInput.value?.dropLast(OPERATION_STRING_PLACE)
                } else {
                    _calculatorInput.value = _calculatorInput.value?.dropLast(1)
                }
                if (_calculatorInput.value == "") {
                    _calculatorInput.value = " "
                }
            }
        }
    }
}
