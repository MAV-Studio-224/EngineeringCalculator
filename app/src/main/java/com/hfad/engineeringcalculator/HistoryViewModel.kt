package com.hfad.engineeringcalculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HistoryViewModel(private val dao:ECDAO) : ViewModel() {
    var allHistory = dao.getAll()
    var list = allHistory.value ?: listOf()

    fun clearHistory() {
        viewModelScope.launch {
            dao.deleteAll(list)
        }
        allHistory = dao.getAll()
        list = listOf()
    }

    fun deleteOneItem(item: ECEntity) {
        viewModelScope.launch {
            dao.delete(item)
            allHistory = dao.getAll()
        }
    }
}