package com.hfad.engineeringcalculator

import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class EquationsViewModelFactory(private val dao: ECDAO): ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EquationsFragmentViewModel::class.java)) {
            return EquationsFragmentViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}