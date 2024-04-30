package com.hfad.engineeringcalculator

import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class HistoryViewModelFactory(val dao: ECDAO): ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}