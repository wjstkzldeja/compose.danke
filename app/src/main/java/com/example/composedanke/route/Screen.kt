package com.example.composedanke.route

/**
 * Represent all Screens (Composables) in the app.
 */
sealed class Screen(
    val route: String
) {
    object LoginScreen : Screen("loginScreen")
    object JoinScreen : Screen("joinScreen")
    object TodayScreen : Screen("todayScreen")
}
