package com.rigelramadhan.beritacompose.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object About: Screen("about")
    object NewsDetail: Screen("detail/{newsId}") {
        fun createRoute(newsId: Int) = "detail/$newsId"
    }
}