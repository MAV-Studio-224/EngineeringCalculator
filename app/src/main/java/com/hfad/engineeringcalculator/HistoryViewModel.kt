package com.hfad.engineeringcalculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HistoryViewModel(private val dao:ECDAO) : ViewModel() {
    private val allHistory = dao.getAll()
    val string = allHistory.map {
        var str = ""
        for (i in it){
            if (it.indexOf(i) == 0 || i.expression != it[it.indexOf(i) - 1].expression) {
                str += "${i.type}\n${i.expression}\n${i.answer}\n\n\n"
            }
        }
        str
    }

    fun clearHistory(){
        viewModelScope.launch {
            dao.deleteAll(allHistory.value!!)
        }
    }
}