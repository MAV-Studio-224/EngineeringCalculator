package com.hfad.engineeringcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

class EquationsFragmentViewModel(val dao: ECDAO): ViewModel() {
    var _a = MutableLiveData("")
    val a: LiveData<Double>
        get() = _a.map { try { it?.toDouble() ?: 1.0 } catch (e: Exception) { 1.0 } }
    var _b = MutableLiveData("")
    val b: LiveData<Double>
        get() = _b.map { try { it?.toDouble() ?: 1.0 } catch (e: Exception) { 1.0 } }
    var _c = MutableLiveData("")
    val c: LiveData<Double>
        get() = _c.map { try { it?.toDouble() ?: 0.0 } catch (e: Exception) { 0.0 } }
    private var _x = MutableLiveData("")
    var type = ""
    private var _roots = MutableLiveData(0)
    val roots: LiveData<Int>
        get() = _roots

    var x0 = 0.0
    var x1 = 0.0
    var x2 = 0.0

    fun getX() {
        when (type) {
            "square" -> {
                val d = (b.value?.pow(2) ?: 0.0) - 4 * (a.value ?: 0.0) * (c.value ?: 0.0)
                if (d > 0.0) {
                    x1 = (((-1 * (b.value ?: 0.0) + sqrt(d)) / (2 * (a.value ?: 1.0))) * 1000).toInt() / 1000.0
                    x2 = (((-1 * (b.value ?: 0.0) - sqrt(d)) / (2 * (a.value ?: 1.0))) * 1000).toInt() / 1000.0
                    _x.value = "x₁ = $x1 \nx₂ = $x2"
                } else if (d < 0.0) {
                    _x.value = "∅"
                } else {
                    x0 = (-1000 * (b.value ?: 0.0) / (2 * (a.value ?: 1.0))).toInt() / 1000.0
                    _x.value = "x = $x0"
                }
                _roots.value = if (d > 0.0) {2} else if (d == 0.0) {1} else {0}
                viewModelScope.launch {
                    dao.insert(ECEntity(type = "Квадратное уравнение",
                        expression = "${a.value}x² + ${b.value}x + ${c.value} = 0",
                        answer = (x.value ?: "ERROR")))
                }
            }
            "linear" -> {
                if (b.value == 0.0 && c.value == 0.0) {
                    _x.value = "x∈(-∞;+∞)"
                } else if (b.value == 0.0 && c.value != 0.0) {
                    _x.value = "∅"
                } else {
                    x0 = (((-1 * (c.value ?: 0.0) / (b.value ?: 1.0)) * 1000).toInt() / 1000.0)
                    _x.value = "x = $x0"
                }
                _roots.value = if (b.value != 0.0) {1} else {0}
                viewModelScope.launch {
                    dao.insert(ECEntity(type = "Линейное уравнение",
                        expression = "${b.value}x + ${c.value} = 0",
                        answer = (x.value ?: "ERROR")))
                }
            }
            else -> {
                viewModelScope.launch {
                    dao.insert(ECEntity(type = "ERROR", expression = "ERROR", answer = "ERROR"))
                }
                _x.value = "ERROR"
            }
        }
    }

    val x: LiveData<String>
        get() = _x.map { it?.toString() ?: "" }
}