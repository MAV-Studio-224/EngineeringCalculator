package com.hfad.engineeringcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.engineeringcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var outputStr = ""

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.button0.setOnClickListener {
            outputStr += "0"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button1.setOnClickListener {
            outputStr += "1"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button2.setOnClickListener {
            outputStr += "2"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button3.setOnClickListener {
            outputStr += "3"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button4.setOnClickListener {
            outputStr += "4"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button5.setOnClickListener {
            outputStr += "5"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button6.setOnClickListener {
            outputStr += "6"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button7.setOnClickListener {
            outputStr += "7"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button8.setOnClickListener {
            outputStr += "8"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.button9.setOnClickListener {
            outputStr += "9"
            binding.textView.text = outputStr
            binding.textView2.text = eval(outputStr).toString()
        }
        binding.buttondot.setOnClickListener {
            if (outputStr != "" && outputStr.toList().last() != ' ' &&
                outputStr.toList().last() != '√') {
                outputStr += "."
                binding.textView.text = outputStr
                binding.textView2.text = eval(outputStr).toString()
            }
        }
        binding.buttonenter.setOnClickListener {
            outputStr = binding.textView2.text.toString()
            binding.textView.text = outputStr
        }
        binding.buttonplus.setOnClickListener {
            if (outputStr != "") {
                outputStr += " + "
                binding.textView.text = outputStr
            }
        }
        binding.buttonminus.setOnClickListener {
            outputStr += " - "
            binding.textView.text = outputStr
        }
        binding.buttonmultiplay.setOnClickListener {
            if (outputStr != "") {
                outputStr += " * "
                binding.textView.text = outputStr
            }
        }
        binding.buttondevide.setOnClickListener {
            if (outputStr != "") {
                outputStr += " / "
                binding.textView.text = outputStr
            }
        }
        binding.buttonsqrt.setOnClickListener {
            outputStr += "√"
            binding.textView.text = outputStr
        }
        binding.buttonc.setOnClickListener {
            outputStr = outputStr.dropLast(1)
            binding.textView.text = outputStr
            binding.textView2.text = if (outputStr != "") eval(outputStr).toString() else ""
        }
        binding.buttonce.setOnClickListener {
            outputStr = ""
            binding.textView.text = ""
            binding.textView2.text = ""
        }
    }

    private fun eval(str: String): Any {
        return try {
            var string = str
            if ('√' in str) {
                for (i in 0..<(str.length-1)) {
                    if (str[i] == '√') {
                        val str1st = string.substringBefore(str[i])
                        string = "SQRT(${string.substringAfter(str[i])}"
                        for (j in 5..string.length) {
                            if (j >= string.length ||
                                string[j] != '1' && string[j] != '2' && string[j] != '3' &&
                                string[j] != '4' && string[j] != '5' && string[j] != '6' &&
                                string[j] != '7' && string[j] != '8' && string[j] != '9'
                            ) {
                                if (j >= string.length) {
                                    string = "$str1st$string)"
                                    break
                                }
                                val strLast = string.substringAfter(string[j])
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
        } catch (e: Exception) {
            "ERROR"
        }
    }
}