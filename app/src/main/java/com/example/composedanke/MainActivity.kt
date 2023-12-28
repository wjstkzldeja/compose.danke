package com.example.composedanke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composedanke.factory.ViewModelFactory
import com.example.composedanke.nav.NavFragment
import com.example.entity.ProjectVo
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory()
        )[MainViewModel::class.java]

        val jsonFile = assets.open("portfolio.json").reader().readText()
        mainViewModel.setProjectList(jsonFile)

        setContent {
            navController = rememberNavController()

            NavFragment(
                mainViewModel,
                navController = navController,
            )
        }
    }
}