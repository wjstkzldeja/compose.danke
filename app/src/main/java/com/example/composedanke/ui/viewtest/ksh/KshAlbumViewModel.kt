package com.example.composedanke.ui.viewtest.ksh

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import com.example.entity.ProjectVo
import com.example.entity.ProjectVoItem
import timber.log.Timber.Forest.d
import java.util.Locale


class KshAlbumViewModel : ViewModel() {
    /** mutable state test
     * edit 동작함*/
    private val _viewModelInputText = mutableStateOf("")
    val viewModelInputText: State<String> = _viewModelInputText

    private val _projectList = mutableStateOf<ArrayList<String>>(arrayListOf())
    val projectList: State<ArrayList<String>> = _projectList

    private val _mutableStateListTest = arrayListOf<ProjectVoItem>().toMutableStateList()
    val mutableStateListTest: List<ProjectVoItem> get() = _mutableStateListTest

    fun initProjectList() {
        val kshAlbum = arrayListOf<String>()
        (1..15).forEach {index ->
            kshAlbum.add("ksh_${index}")
        }
        d("logTest : ${kshAlbum}")
        _projectList.value = kshAlbum
    }

    @JvmName("setText1")
    fun setText(setText: String) {
        _viewModelInputText.value = setText
        d("searchTest textValue 2 : ${_viewModelInputText.value}")
    }


    /** image size 비트맵으로 변환
     * 비율은 좀더 계산이 필요*/
    fun calculateInSampleSize(imageBitmap: ImageBitmap): ImageBitmap {
        d("logTestBitmap : ${imageBitmap.width}")
        d("logTestBitmap : ${imageBitmap.height}")
        val width = (imageBitmap.width * 0.4).toInt()
        val height = (imageBitmap.height * 0.4).toInt()
//         val resizedBitmap = Bitmap.createScaledBitmap(
//             imageBitmap.asAndroidBitmap() , imageBitmap.height/4, imageBitmap.height/4, true
//         ).asImageBitmap()

        val resizedBitmap = Bitmap.createScaledBitmap(
            imageBitmap.asAndroidBitmap(), width, height, true
        ).asImageBitmap()
        return resizedBitmap
    }

    fun calculateInSampleSizeImageList(imageBitmap: ImageBitmap): ImageBitmap {
        d("logTestBitmap : ${imageBitmap.width}")
        d("logTestBitmap : ${imageBitmap.height}")
        val width = (imageBitmap.width * 0.2).toInt()
        val height = (imageBitmap.height * 0.2).toInt()
//         val resizedBitmap = Bitmap.createScaledBitmap(
//             imageBitmap.asAndroidBitmap() , imageBitmap.height/4, imageBitmap.height/4, true
//         ).asImageBitmap()

        val resizedBitmap = Bitmap.createScaledBitmap(
            imageBitmap.asAndroidBitmap(), width, height, false
        ).asImageBitmap()
        return resizedBitmap
    }
}