package com.danielfreitassc.calculadoraappkotlin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel() {
    private val _equationText = MutableLiveData("")
    val equationText : LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText : LiveData<String> = _resultText

    fun onButtonClick(btn: String) {
        Log.i("Botão clicado", btn)

        _equationText.value?.let {
            if(btn == "AC") {
                _equationText.value = ""
                _equationText.value = ""
                return
            }

            if(btn == "C") {
                if(it.isNotEmpty()) {
                    _equationText.value = it.substring(0,it.length-1)
                }
                return
            }

            if(btn == "=") {
                _equationText.value = _resultText.value
                return
            }

            _equationText.value = it+btn

            try {
                _resultText.value = caculateResult(_equationText.value.toString())
            }catch (_ : Exception){}

        }

    }

    fun caculateResult(equation: String) : String {
        val context : Context = Context.enter()
        context.optimizationLevel = -1
        val scriptable : Scriptable = context.initStandardObjects()
        var finalResult = context.evaluateString(scriptable,equation,"Javascript",1,null).toString()
        if(finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0", "")
        }
        return  finalResult
    }
}