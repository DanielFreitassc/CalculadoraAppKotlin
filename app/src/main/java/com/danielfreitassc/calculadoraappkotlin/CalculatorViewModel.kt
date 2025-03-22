package com.danielfreitassc.calculadoraappkotlin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val _equationText = MutableLiveData("")
    val equationText : LiveData<String>

    fun onButtonClick(btn: String) {
        Log.i("Botão clicado", btn)
    }
}