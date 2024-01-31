package com.example.composedanke.route

/**
 * Represent all Screens (Composables) in the app.
 */
sealed class Screen(
    val route: String
) {
    /** 홈 화면*/
    object HomeScreen : Screen("homeScreen")

    /** 화면 테스트*/
    object SearchScreen : Screen("searchScreen")
    object SearchDetailScreen : Screen("searchDetailScreen")

    /** 클릭 테스트*/
    object ClickOneScreen : Screen("clickOneScreen")
    object ClickTwoScreen : Screen("clickTwoScreen")
    object ClickThreeScreen : Screen("clickThreeScreen")

    /** 화면 테스트*/
    object LazyScreen : Screen("lazyScreen")
    object WellnessScreen : Screen("wellnessScreen")

    /** 당케 테스트*/
    object LoginScreen : Screen("loginScreen")
    object JoinScreen : Screen("joinScreen")
    object TodayScreen : Screen("todayScreen")

    /** ksh album*/
    object KshAlbumScreen : Screen("kshAlbumScreen")
}
