package com.example.composedanke.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedanke.route.Screen
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.example.composedanke.ui.viewtest.lazy.LazyScreen
import com.example.composedanke.ui.viewtest.search.SearchScreen
import com.example.composedanke.ui.viewtest.wellness.WellnessScreen

private val _obsTest = MutableLiveData<String>("")
val obsTest: LiveData<String> = _obsTest

/**
 * 안드 컴포즈 튜토리얼 : https://developer.android.com/courses/jetpack-compose/course?hl=ko
 * 간단한 튜토리얼 블로그 : https://yoon-dailylife.tistory.com/127
 * 네비 블로그 1 : https://whyprogrammer.tistory.com/652
 * 네비 블로그 2 : https://jinhyun.blog/android/jetpack-compose/navigation/
 * */
@Composable
fun NavFragment(
    navController: NavHostController = rememberNavController()
) {
    /** 클릭 screen*/
//    ComposeDankeTheme {
//        NavHost(
//            navController = navController,
//            startDestination = Screen.ClickOneScreen.route
//        ) {
//            composable(Screen.ClickOneScreen.route) {
//                val viewModel: ClickOneViewModel = viewModel(
//                    factory = ClickOneViewModel.Factory
//                )
//                ClickOneScreen(
//                    onClick = {
//                        navController.navigateSingleTopTo(route = Screen.ClickTwoScreen.route)
//                    })
//            }
//            /** navController 직접 전달은 권장하지 않음*/
//            composable(Screen.ClickTwoScreen.route) {
//                val viewModel: ClickTwoViewModel = viewModel(
//                    factory = ClickTwoViewModel.Factory
//                )
//                _obsTest.value = "옵저버 전달 초기화"
//                ClickTwoScreen(
//                    onClickOne = {
//                        _obsTest.value = "옵저버 전달"
//                    },
//                    onThreeClick = {
//                        navController.navigateSingleTopTo(route = Screen.ClickThreeScreen.route)
//                    },
//                )
//            }
//
//            composable(Screen.ClickThreeScreen.route) {
//                val viewModel: ClickThreeViewModel = viewModel(
//                    factory = ClickThreeViewModel.Factory
//                )
//                ClickThreeScreen(
//                )
//            }
//        }
//    }

    /** 검색 screen*/
    ComposeDankeTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.LazyScreen.route
        ) {
            composable(Screen.SearchScreen.route) {
                SearchScreen(
                    onNextView = {})
            }
            composable(Screen.WellnessScreen.route) {
                WellnessScreen()
            }
            composable(Screen.LazyScreen.route) {
                LazyScreen()
            }
        }
    }

    /** 당케 screen*/
//    ComposeDankeTheme {
//        NavHost(
//            navController = navController,
//            startDestination = Screen.LoginScreen.route
//        ) {
//            composable(Screen.LoginScreen.route) {
//                val viewModel: LoginViewModel = viewModel(
//                    factory = LoginViewModel.Factory
//                )
//                LoginScreen(
//                    viewModel,
//                    onClick = {
//                    })
//            }
//            /** navController 직접 전달은 권장하지 않음*/
//            composable(Screen.JoinScreen.route) {
//                val viewModel: JoinViewModel = viewModel(
//                    factory = JoinViewModel.Factory
//                )
//                _obsTest.value = "옵저버 전달 초기화"
//                JoinScreen(
//                    viewModel,
//                    onClickOne = {
//                    },
//                )
//            }
//
//            composable(Screen.TodayScreen.route) {
//                val viewModel: TodayViewModel = viewModel(
//                    factory = TodayViewModel.Factory
//                )
//                TodayScreen(
//                    viewModel,
//                )
//            }
//        }
//    }
}

/** 네비게이션 중복 클릭 방지*/
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }