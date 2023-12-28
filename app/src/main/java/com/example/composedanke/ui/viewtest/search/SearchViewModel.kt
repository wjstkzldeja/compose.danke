package com.example.composedanke.ui.viewtest.search

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


class SearchViewModel : ViewModel() {
    /** mutable state test
     * edit 동작함*/
    private val _viewModelInputText = mutableStateOf("")
    val viewModelInputText: State<String> = _viewModelInputText

    private val _projectListOrigin = mutableStateOf<ProjectVo>(ProjectVo())
    val projectListOrigin: State<ProjectVo> = _projectListOrigin

    private val _projectList = mutableStateOf<ProjectVo>(ProjectVo())
    val projectList: State<ProjectVo> = _projectList

    private val _mutableStateListTest = arrayListOf<ProjectVoItem>().toMutableStateList()
    val mutableStateListTest: List<ProjectVoItem> get() = _mutableStateListTest

//    private val _searchList = arrayListOf("1", "2", "3", "4").toMutableStateList()
//    val searchList: List<String> get() = _searchList

//    private val _imageList = arrayListOf("1", "2", "3", "4").toMutableStateList()
//    val imageList: List<String> get() = _imageList

    fun initProjectList(projectList: ProjectVo) {
        _mutableStateListTest.addAll(projectList)
        _projectList.value = projectList
        _projectListOrigin.value = projectList
    }

    @JvmName("setText1")
    fun setText(setText: String) {
        _viewModelInputText.value = setText
        d("searchTest textValue 2 : ${_viewModelInputText.value}")
    }

    fun changeList() {
        /**검색어 Null -> 전체 리스트로 초기화 */
        if (viewModelInputText.value.isNullOrEmpty()) {
            _projectList.value = _projectListOrigin.value
            return
        }

        /** 검색시 전체 리스트로 초기화*/
        _projectList.value = _projectListOrigin.value

        /** 검색 영역*/
        val projectList: ProjectVo = ProjectVo()
        val searchList = _projectList.value.filter {
            val projectName = (it.project).uppercase(Locale("en"))
            val searchText = (viewModelInputText.value).uppercase(Locale("en"))
            val tag = it.tags.map { it.uppercase(Locale("en")) }
            /*d("searchTest input filter test : projectName : ${projectName}")
            d("searchTest input filter test : tags : ${it.tags}")
            d("searchTest input filter test : tags -------------------------")*/
            /*d("searchTest input search boolean test : projectName : ${projectName.contains(searchText)}")
            d("searchTest input search boolean test : tag : ${tag.contains(searchText)}")
            d("searchTest input search boolean test : boolean : ${projectName.contains(searchText) || tag.contains(searchText)}")
            d("searchTest input search boolean test ---------------------------------------------")*/
            projectName.contains(searchText) || tag.contains(searchText)
        }
        d("searchTest input searchList size : ${searchList.size}")
        projectList.addAll(searchList)
        _projectList.value = projectList
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
             imageBitmap.asAndroidBitmap() , width, height, true
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
            imageBitmap.asAndroidBitmap() , width, height, false
        ).asImageBitmap()
        return resizedBitmap
    }
}