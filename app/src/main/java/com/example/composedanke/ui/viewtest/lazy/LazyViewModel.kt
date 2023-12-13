package com.example.composedanke.ui.viewtest.lazy

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class LazyViewModel : ViewModel() {
    private val _lazyList = getLazyList().toMutableStateList()
    val lazyList: List<LazyVo> get() = _lazyList

}

private fun getLazyList() = List(30) { i ->
    LazyVo(i, "Task # $i")
}