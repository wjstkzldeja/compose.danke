package com.example.composedanke.route

/**
 * Represent all Screens (Composables) in the app.
 */
sealed class Screen(
    val route: String
) {
    /** 클릭 테스트*/
    object ClickOneScreen : Screen("clickOneScreen")
    object ClickTwoScreen : Screen("clickTwoScreen")
    object ClickThreeScreen : Screen("clickThreeScreen")

    /** 화면 테스트*/
    object SearchScreen : Screen("searchScreen")

    /** 당케 테스트*/
    object LoginScreen : Screen("loginScreen")
    object JoinScreen : Screen("joinScreen")
    object TodayScreen : Screen("todayScreen")
}
