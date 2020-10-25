package com.example.androidcalculator.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidcalculator.R
import com.example.androidcalculator.databinding.CalculatorFragmentBinding

class CalculatorFragment : Fragment() {

    private lateinit var viewModel: CalculatorViewModel

    private lateinit var binding: CalculatorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.calculator_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        binding.calculatorViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.isWrongInput.observe(viewLifecycleOwner, Observer { isWrongInput ->
            if (isWrongInput) {
                Toast.makeText(this.context, "wrong input", Toast.LENGTH_SHORT).show()
                viewModel.onWrongInputErrorDisplayed()
            }
        })

        return binding.root
    }
}
