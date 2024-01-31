package com.example.composedanke.nav

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composedanke.MainViewModel
import com.example.composedanke.route.Screen
import com.example.composedanke.ui.clicktest.ClickOneScreen
import com.example.composedanke.ui.clicktest.ClickOneViewModel
import com.example.composedanke.ui.clicktest.ClickThreeScreen
import com.example.composedanke.ui.clicktest.ClickThreeViewModel
import com.example.composedanke.ui.clicktest.ClickTwoScreen
import com.example.composedanke.ui.clicktest.ClickTwoViewModel
import com.example.composedanke.ui.danke.join.JoinScreen
import com.example.composedanke.ui.danke.join.JoinViewModel
import com.example.composedanke.ui.danke.login.LoginScreen
import com.example.composedanke.ui.danke.login.LoginViewModel
import com.example.composedanke.ui.danke.today.TodayScreen
import com.example.composedanke.ui.danke.today.TodayViewModel
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.example.composedanke.ui.viewtest.home.HomeScreen
import com.example.composedanke.ui.viewtest.ksh.KshAlbumScreen
import com.example.composedanke.ui.viewtest.lazy.LazyScreen
import com.example.composedanke.ui.viewtest.search.SearchScreen
import com.example.composedanke.ui.viewtest.search.detail.SearchDetailScreen
import com.example.composedanke.ui.viewtest.wellness.WellnessScreen
import com.example.entity.ProjectVoItem
import com.google.gson.Gson
import timber.log.Timber.Forest.d

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
    mainViewModel: MainViewModel,
    navController: NavHostController = rememberNavController()
) {
//    private val _projectList = mutableStateOf<ProjectVo>(ProjectVo())
//    val projectList: State<ProjectVo> = _projectList
//    val rainbowColorsBrush1 = remember { mutableListOf() }
    var vo: ProjectVoItem = ProjectVoItem(
        imgs = arrayListOf("testImage", "testImage", "testImage"),
        project = "title",
        tags = arrayListOf("1", "2", "3")
    )

    /** home*/
    ComposeDankeTheme {
        NavHost(
            navController = navController,
//            startDestination = Screen.JoinScreen.route
            startDestination = Screen.HomeScreen.route
        ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(
                    onClickSearchView = { navController.navigateSingleTopTo(route = Screen.SearchScreen.route) },
                    onClickWellnessView = { navController.navigateSingleTopTo(route = Screen.WellnessScreen.route) },
                    onClickLazyView = { navController.navigateSingleTopTo(route = Screen.LazyScreen.route) },
                    onClickOneView = { navController.navigateSingleTopTo(route = Screen.ClickOneScreen.route) },
                    onClickDankeView = { navController.navigateSingleTopTo(route = Screen.LoginScreen.route) },
                    onClickKshAlbumView = { navController.navigateSingleTopTo(route = Screen.KshAlbumScreen.route) },
                )
            }
            /** 검색 screen @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

            /**https://developer.android.com/jetpack/compose/navigation?hl=ko
             *  Argument 넘기는 방식
             * - url get 방식과 비슷함
             * - 단일 값 = route/value, 다중 값 = route?value=value
             * - route 뒤에 보낼 값 작성*/
            composable(Screen.SearchScreen.route) {
                SearchScreen(
                    mainViewModel.getProjectList(),
                    onClickSearchDetailView = { projectVoItem ->
                        vo = projectVoItem
                        val proVo = Gson().toJson(projectVoItem)
                        /*navController.navigate(route = "${Screen.SearchDetailScreen.route}?value=test")      {
                            launchSingleTop = true
                        }*/
                        navController.navigateSingleTopTo(route = "${Screen.SearchDetailScreen.route}?value=${proVo}")
                    },
                )
            }
            /** Argument 받는 방식
             * - url get 방식과 비슷함
             * - route 뒤에 받을 값 작성
             * - NavBackStackEntry에서 arguments get*/
            composable(
                "${Screen.SearchDetailScreen.route}?value={value}",
                arguments = listOf(
                    navArgument("value") {
                        type = NavType.StringType
                        defaultValue = "Default"
                    },
                ),
                enterTransition = {
                    /*     slideInVertically(
                             initialOffsetY = { it },
                             animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
                         )*/
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
                    )
                },
            ) {
                val arr = it.arguments?.getString("value")
                val arrFromJson = Gson().fromJson(arr, ProjectVoItem::class.java)
                d("argTest arrFromJson : ${vo}")
                SearchDetailScreen(projectVoItem = arrFromJson, projectVoItem2 = vo)
            }
            /** 리스트뷰 테스트 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
            composable(Screen.WellnessScreen.route) {
                WellnessScreen()
            }
            composable(Screen.LazyScreen.route) {
                LazyScreen()
            }
            /** 클릭 테스트 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
            composable(Screen.ClickOneScreen.route) {
                val viewModel: ClickOneViewModel = viewModel(
                    factory = ClickOneViewModel.Factory
                )
                ClickOneScreen(
                    onClick = {
                        navController.navigateSingleTopTo(route = Screen.ClickTwoScreen.route)
                    })
            }
            /** navController 직접 전달은 권장하지 않음*/
            composable(Screen.ClickTwoScreen.route) {
                val viewModel: ClickTwoViewModel = viewModel(
                    factory = ClickTwoViewModel.Factory
                )
                _obsTest.value = "옵저버 전달 초기화"
                ClickTwoScreen(
                    onClickOne = {
                        _obsTest.value = "옵저버 전달"
                    },
                    onThreeClick = {
                        navController.navigateSingleTopTo(route = Screen.ClickThreeScreen.route)
                    },
                )
            }

            composable(Screen.ClickThreeScreen.route) {
                val viewModel: ClickThreeViewModel = viewModel(
                    factory = ClickThreeViewModel.Factory
                )
                ClickThreeScreen()
            }
            /** 당케 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
            composable(Screen.LoginScreen.route) {
                val viewModel: LoginViewModel = viewModel(
                    factory = LoginViewModel.Factory
                )
                LoginScreen(
                    viewModel,
                    onJoinView = {
                        navController.navigateSingleTopTo(route = Screen.JoinScreen.route)
                    })
            }
            /* navController 직접 전달은 권장하지 않음*/
            composable(Screen.JoinScreen.route) {
                val viewModel: JoinViewModel = viewModel(
                    factory = JoinViewModel.Factory
                )
                _obsTest.value = "옵저버 전달 초기화"
                JoinScreen(
                    viewModel,
                    onClickToday = {
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
            /** ksh album screen @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
            composable(Screen.KshAlbumScreen.route) {
                KshAlbumScreen()
            }
        }
    }
}

/** 네비게이션 중복 클릭 방지*/
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }