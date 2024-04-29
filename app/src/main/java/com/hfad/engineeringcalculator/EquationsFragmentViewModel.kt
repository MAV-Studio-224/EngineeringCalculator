package com.hfad.engineeringcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import kotlin.math.pow
import kotlin.math.sqrt

class EquationsFragmentViewModel: ViewModel() {
    var _a = MutableLiveData("")
    val a: LiveData<Double>
        get() = _a.map { try { it?.toDouble() ?: 1.0 } catch (e: Exception) { 1.0 } }
    var _b = MutableLiveData("")
    val b: LiveData<Double>
        get() = _b.map { try { it?.toDouble() ?: 1.0 } catch (e: Exception) { 1.0 } }
    var _c = MutableLiveData("")
    val c: LiveData<Double>
        get() = _c.map { try { it?.toDouble() ?: 0.0 } catch (e: Exception) { 0.0 } }
    var _x = MutableLiveData("")
    var type = ""

    fun getX() {
        when (type) {
            "square" -> {
                val d = (b.value?.pow(2) ?: 0.0) - 4 * (a.value ?: 0.0) * (c.value ?: 0.0)
                _x.value = if (d > 0.0) {
                    "x₁ = ${(((-1 * (b.value ?: 0.0) + sqrt(d)) / (2 * (a.value ?: 1.0))) * 1000).toInt() / 1000.0} \n" +
                            "x₂ = ${(((-1 * (b.value ?: 0.0) - sqrt(d)) / (2 * (a.value ?: 1.0))) * 1000).toInt() / 1000.0}"
                } else if (d < 0.0) {
                    "∅"
                } else {
                    "x = ${(-1000 * (b.value?.pow(2) ?: 0.0) / (2 * (a.value ?: 1.0))).toInt() / 1000.0}"
                }
            }
            "linear" -> {
                _x.value = if (b.value == 0.0 && c.value == 0.0) {
                    "x∈(-∞;+∞)"
                } else if (b.value == 0.0 && c.value != 0.0) {
                    "∅"
                } else {
                    "x = ${(((-1 * (c.value ?: 0.0) / (b.value ?: 1.0)) * 1000).toInt() / 1000.0)}"
                }
            }
            else -> _x.value = "ERROR"
        }
    }

    val x: LiveData<String>
        get() = _x.map { it?.toString() ?: "" }
}