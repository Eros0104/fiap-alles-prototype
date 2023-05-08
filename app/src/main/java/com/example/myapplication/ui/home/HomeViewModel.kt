package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private val _textBalance = MutableLiveData<String>().apply {
        value = ""
    }

    val textBalance: LiveData<String> = _textBalance

    fun setTextBalance(value: String) {
        _textBalance.value = value
    }
}
