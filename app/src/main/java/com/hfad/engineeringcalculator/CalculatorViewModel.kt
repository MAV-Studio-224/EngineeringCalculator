package com.hfad.engineeringcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel(){
    private var _outputStr = MutableLiveData("")
    val outputStr: LiveData<String>
        get() = _outputStr
    private var _result = MutableLiveData("")
    val result: LiveData<String>
        get() = _result

    private fun eval(str: String?): Any {
        return try {
            if (str != null) {
                var string: String = str
                if ('√' in str) {
                    for (i in 0..<(str.length)) {
                        if (str[i] == '√') {
                            val str1st = string.substringBefore(str[i])
                            string = "SQRT(${string.substringAfter(str[i])}"
                            for (j in 5..string.length) {
                                if (j >= string.length ||
                                    string[j] != '1' && string[j] != '2' && string[j] != '3' &&
                                    string[j] != '4' && string[j] != '5' && string[j] != '6' &&
                                    string[j] != '7' && string[j] != '8' && string[j] != '9' &&
                                    string[j] != '0'
                                ) {
                                    if (j >= string.length) {
                                        string = "$str1st$string)"
                                        break
                                    }
                                    val strLast = string.substring(j)
                                    string = "${string.substringBefore(string[j])})"
                                    string = "$str1st$string$strLast"
                                    break
                                }
                            }
                        }
                    }
                }
                val r = com.ezylang.evalex.Expression(string)
                val l = r.evaluate().numberValue.toDouble()
                if (l.toInt().toDouble() == l) {
                    l.toInt()
                } else {
                    kotlin.math.round(l * 10000000) / 10000000
                }
            } else throw Exception("ERROR")
        } catch (e: Exception) {
            "ERROR"
        }
    }

    fun addDot(){
        if (outputStr.value != "" && (outputStr.value?.last() ?: ' ') != ' ' &&
            (outputStr.value?.last() ?: '.') != '.' && (outputStr.value?.last() ?: '√') != '√') {
            _outputStr.value = "${outputStr.value}."
        } else if (outputStr.value != "" && (outputStr.value?.last() ?: ' ') != ' ' &&
            ((outputStr.value?.last() ?: '.') == '.' && (outputStr.value?.last() ?: '√') == '√')) {
            _outputStr.value = "${outputStr.value}0."
        }
    }

    private fun add(str: String){
        _outputStr.value += str
    }

    private fun addResult(){
        _result.value = eval(outputStr.value).toString()
    }

    fun addAll(str: String){
        add(str)
        addResult()
    }

    fun addSymbol(str: String){
        if (outputStr.value != "") {
            _outputStr.value = "${outputStr.value}$str"
        }
    }

    fun result() {
        _outputStr.value = result.value
    }

    fun clear(){
        _outputStr.value = ""
        _result.value = ""
    }

    fun delete() {
        _outputStr.value = _outputStr.value?.dropLast(1)
        addResult()
    }
}