package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun OnDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun ondecimalpoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput.append(".")
            lastDot = true
            lastNumeric = false
        }
    }



    private fun removezero(result : String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    // once equal button is clicked
    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValus = tvInput.text.toString()
            var prefix=""
            try {
                if (tvValus.startsWith("-")){
                    prefix = "-"
                    tvValus = tvValus.substring(1)
                }

                if (tvValus.contains("-")) {
                    val splitValue = tvValus.split("-")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(!prefix.isEmpty()){
                        one=prefix + one
                    }

                    tvInput.text = removezero((one.toDouble()-two.toDouble()).toString())

                     }

                else if(tvValus.contains("+")) {
                    val splitValue = tvValus.split("+")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(!prefix.isEmpty()){
                        one=prefix + one
                    }

                    tvInput.text = removezero((one.toDouble()+two.toDouble()).toString())

                }

                else if (tvValus.contains("*")) {
                    val splitValue = tvValus.split("*")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(!prefix.isEmpty()){
                        one=prefix + one
                    }

                    tvInput.text = removezero((one.toDouble()*two.toDouble()).toString())

                }
                else if (tvValus.contains("/")) {
                    val splitValue = tvValus.split("/")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(!prefix.isEmpty()){
                        one=prefix + one
                    }

                    tvInput.text = removezero((one.toDouble()/two.toDouble()).toString())

                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }

    }

    fun onOperator(view: View) {

        if (lastNumeric && !isOperatorAdded(tvInput.text.toString()))
            tvInput.append((view as Button).text)
        lastNumeric = false
        lastDot = false
    }


    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}