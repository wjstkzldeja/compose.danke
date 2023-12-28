package com.example.composedanke

import androidx.lifecycle.ViewModel
import com.example.entity.ProjectVo
import com.example.entity.ProjectVoItem
import com.google.gson.Gson
import timber.log.Timber.Forest.d

class MainViewModel : ViewModel() {
    private val projectList: ProjectVo = ProjectVo()

    fun setProjectList(jsonFile: String) {
        projectList.addAll(Gson().fromJson(jsonFile, ProjectVo::class.java))
    }

    fun getProjectList(): ProjectVo {
        return projectList
    }
}
