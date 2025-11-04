package com.example.redandbluesquares

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _items = mutableStateListOf<Int>()
    val items: List<Int> get() = _items

    init {
        repeat(2) { _items.add(it) }
    }

    fun addItem() {
        _items.add(_items.size)
    }
}