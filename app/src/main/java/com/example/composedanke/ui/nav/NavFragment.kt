package com.example.composedanke.ui.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedanke.route.Screen
import com.example.composedanke.ui.join.JoinScreen
import com.example.composedanke.ui.join.JoinViewModel
import com.example.composedanke.ui.login.LoginScreen
import com.example.composedanke.ui.login.LoginViewModel
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.example.composedanke.ui.today.TodayScreen
import com.example.composedanke.ui.today.TodayViewModel

private val _obsTest = MutableLiveData<String>("")
val obsTest: LiveData<String> = _obsTest

@Composable
fun NavFragment(
    navController: NavHostController = rememberNavController()
) {
    ComposeDankeTheme {
        NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
            composable(Screen.LoginScreen.route) {
                val viewModel: LoginViewModel = viewModel(
                    factory = LoginViewModel.Factory
                )
                LoginScreen(
                    viewModel,
                    onClick = {
                        navController.navigate(route = Screen.JoinScreen.route)
                    })
            }
            composable(Screen.JoinScreen.route) {
                val viewModel: JoinViewModel = viewModel(
                    factory = JoinViewModel.Factory
                )
                _obsTest.value = "옵저버 전달 초기화"
                JoinScreen(
                    viewModel,
                    navController,
                    onClickOne = {
                        _obsTest.value = "옵저버 전달"
                        navController.navigate(route = Screen.TodayScreen.route)
                    },
                )
            }

            composable(Screen.TodayScreen.route) {
                val viewModel: TodayViewModel = viewModel(
                    factory = TodayViewModel.Factory
                )
                TodayScreen(
                    viewModel,
                )
            }
        }
    }
}