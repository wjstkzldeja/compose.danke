package com.example.composedanke.ui.danke.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.composedanke.Application

class LoginViewModel : ViewModel() {
    companion object {
        val Factory = viewModelFactory {
            initializer {
                LoginViewModel()
            }
        }
    }
}