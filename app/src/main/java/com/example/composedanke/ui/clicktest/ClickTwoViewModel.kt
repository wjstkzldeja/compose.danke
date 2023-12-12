package com.example.composedanke.ui.clicktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class ClickTwoViewModel : ViewModel() {
    private val _viewModelText = MutableLiveData<String>()
    val viewModelText: LiveData<String> = _viewModelText

    companion object {
        val Factory = viewModelFactory {
            initializer {
                ClickTwoViewModel()
            }
        }
    }

    @JvmName("setText1")
    fun setText(setText: String) {
        _viewModelText.value = setText
    }
}