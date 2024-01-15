package com.example.composedanke.ui.danke.join

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class JoinViewModel : ViewModel() {
    companion object {
        val Factory = viewModelFactory {
            initializer {
                JoinViewModel()
            }
        }
    }
    private val _progressBar = mutableStateOf(0.25f)
    val progressBar: State<Float> = _progressBar

    private val _nameText = mutableStateOf("")
    val nameText: State<String> = _nameText

    private val _nameTextLength = mutableStateOf("0")
    val nameTextLength: State<String> = _nameTextLength

    private val _codeText = mutableStateOf("")
    val codeText: State<String> = _codeText

    private val _codeTextLength = mutableStateOf("0")
    val codeTextLength: State<String> = _codeTextLength

    private val _tallValue = mutableStateOf("0")
    val tallValue: State<String> = _tallValue

    fun setNameText(setText: String) {
        _nameText.value = setText
    }

    fun setNameTextLength(setText: String) {
        _nameTextLength.value = setText
    }


    fun setCodeText(setText: String) {
        _codeText.value = setText
    }

    fun setCodeTextLength(setText: String) {
        _codeTextLength.value = setText
    }

    fun setProgressBar(progressValue: Float) {
        _progressBar.value = progressValue
    }

    fun setTallValue(tallValue: String) {
        _tallValue.value = tallValue
    }

}