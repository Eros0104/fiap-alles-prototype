package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Category

class HomeViewModel : ViewModel() {
    private val _textBalance = MutableLiveData<String>()
    private val _balanceData = mutableMapOf<Category, MutableLiveData<String>>()

    init {
        _textBalance.apply { value = "" }
        Category.values().forEach { category ->
            _balanceData[category] = MutableLiveData<String>().apply { value = "" }
        }
    }

    val textBalance: LiveData<String> = _textBalance
    val textBalances: Map<Category, LiveData<String>>
        get() = _balanceData

    fun setTextBalance(value: String) {
        _textBalance.value = value
    }
    fun setTextCategoryBalance(category: Category, value: String) {
        _balanceData[category]?.value = value
    }
}
