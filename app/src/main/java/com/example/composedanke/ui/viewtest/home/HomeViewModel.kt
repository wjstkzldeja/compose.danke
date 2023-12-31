package com.example.composedanke.ui.viewtest.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import timber.log.Timber.Forest.d

class HomeViewModel : ViewModel() {
    /** Live data test
     * edit 텍스트 동작을 안함*/
    private val _liveDataTest = MutableLiveData<String>()
    val liveDataTest: LiveData<String> = _liveDataTest

    /** mutable state test
     * edit 동작함*/
    private val _viewModelInputText = mutableStateOf("")
    val viewModelInputText: State<String> = _viewModelInputText

    private val _searchList = mutableStateOf<List<String>>(arrayListOf("1", "2", "3", "4"))
    val searchList: State<List<String>> = _searchList

//    private val _searchList = arrayListOf("1", "2", "3", "4").toMutableStateList()
//    val searchList: List<String> get() = _searchList

    companion object {
        val Factory = viewModelFactory {
            initializer {
                HomeViewModel()
            }
        }
    }

    @JvmName("setText1")
    fun setText(setText: String) {
        _viewModelInputText.value = setText
        d("logTest textValue 2 : ${_viewModelInputText.value}")
    }

    fun changeList() {
        d("logTest : inputText : ${viewModelInputText.value}")
        _searchList.value = arrayListOf(
            "change_1",
            "change_2",
            "change_3",
            "change_4",
            "change_1",
            "change_2",
            "change_3",
            "change_4",
            "change_1",
            "change_2",
            "change_3",
            "change_4"
        )
    }
}