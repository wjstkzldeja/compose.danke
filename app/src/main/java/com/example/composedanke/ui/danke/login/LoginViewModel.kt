package com.example.composedanke.ui.danke.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.composedanke.Application
import com.example.composedanke.R

class LoginViewModel : ViewModel() {
    companion object {
        val Factory = viewModelFactory {
            initializer {
                LoginViewModel()
            }
        }
    }

    private val _content = mutableStateOf(R.string.content_1)
    val content: State<Int> = _content

    private val _contentSub = mutableStateOf(R.string.content_sub_1)
    val contentSub: State<Int> = _contentSub

    private val _isSheetOpen = mutableStateOf(false)
    val isSheetOpen: State<Boolean> = _isSheetOpen

    fun changeContentText(pager: Int) {
        when (pager) {
            0 -> {
                _content.value = R.string.content_1
                _contentSub.value = R.string.content_sub_1
            }

            1 -> {
                _content.value = R.string.content_2
                _contentSub.value = R.string.content_sub_2
            }

            2 -> {
                _content.value = R.string.content_3
                _contentSub.value = R.string.content_sub_3
            }
        }
    }

    fun setSheetState(status: Boolean) {
        _isSheetOpen.value = status
    }

}