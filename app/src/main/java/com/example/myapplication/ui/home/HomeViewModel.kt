package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private val _textWelcome = MutableLiveData<String>().apply {
        value = ""
    }

    private val _textBalance = MutableLiveData<String>().apply {
        value = ""
    }

    val textWelcome: LiveData<String> = _textWelcome

    val textBalance: LiveData<String> = _textBalance
}
