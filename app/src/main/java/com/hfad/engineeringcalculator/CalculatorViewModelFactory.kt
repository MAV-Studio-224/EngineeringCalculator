package com.hfad.engineeringcalculator

import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class CalculatorViewModelFactory(private val dao: ECDAO): ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            return CalculatorViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}