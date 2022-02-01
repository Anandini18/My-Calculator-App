package com.nandini.android.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var tvInput: TextView? = null
    var lastNumeric=false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onBackSp (view: android.view.View) {
        var backspace=tvInput?.text.toString()
        if(!backspace.isEmpty())
        {
            backspace=backspace.substring(0,backspace.length-1)
            tvInput?.setText(backspace)

        }

    }

    fun onDigit(view: android.view.View) {
        tvInput=findViewById(R.id.tvInput)
        tvInput?.append((view as Button).text)
        lastNumeric=true
    }

    fun onClear (view: android.view.View)
    {
        tvInput=findViewById(R.id.tvInput)
        tvInput?.text=""
        lastNumeric=false
        lastDot=false
    }

    fun removeZeroAfterDot(result: String) : String
    {
        var value=result
        if(result.contains(".0"))
        {
            value=result.substring(0,result.length-2)
        }
        return value
    }

    fun onEquals(view: android.view.View)
    {
        if(lastNumeric){

            var tvValue=tvInput?.text.toString()
            var prefix=""

            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)

                }

                if(tvValue.contains("-")){
                    var splitValue = tvValue.split("-")

                    var one=splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }

                    tvInput?.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                }
               else if(tvValue.contains("+")){
                    var splitValue = tvValue.split("+")

                    var one=splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }

                    tvInput?.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }
                if(tvValue.contains("÷")){
                    var splitValue = tvValue.split("÷")

                    var one=splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }

                    tvInput?.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                }
                if(tvValue.contains("%")){
                    var splitValue = tvValue.split("%")

                    var one=splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }

                    tvInput?.text=removeZeroAfterDot((one.toDouble()%two.toDouble()).toString())
                }
                if(tvValue.contains("×")){
                    var splitValue = tvValue.split("×")

                    var one=splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }

                    tvInput?.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }

            }catch (e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }



    fun onDecimal(view : android.view.View)
    {
        tvInput=findViewById(R.id.tvInput)
        if(lastNumeric == true && lastDot == false)
        {
            // if (lastNumeric && !lastDot)
            tvInput?.append(".")
        }
        lastDot=true
        lastNumeric=false
    }

    fun onOperator(view : android.view.View)
    {
        if(lastNumeric && !isOperatorAdded(tvInput?.text.toString()))
        {
            tvInput?.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }

    fun isOperatorAdded(value:String) : Boolean
    {
       return if(tvInput?.text.toString().startsWith("-"))
        {
          false
        }
        else{
            value.contains("+") || value.contains("÷")||  value.contains("×")|| value.contains("-") || value.contains("%")
       }
    }



}