package com.rigelramadhan.beritacompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rigelramadhan.beritacompose.ui.navigation.NavigationItem
import com.rigelramadhan.beritacompose.ui.navigation.Screen
import com.rigelramadhan.beritacompose.ui.screen.about.AboutScreen
import com.rigelramadhan.beritacompose.ui.screen.detail.DetailScreen
import com.rigelramadhan.beritacompose.ui.screen.home.HomeScreen
import com.rigelramadhan.beritacompose.ui.theme.BeritaComposeTheme

@Composable
fun BeritaComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val uriHandler = LocalUriHandler.current

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.NewsDetail.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { newsId ->
                    navController.navigate(Screen.NewsDetail.createRoute(newsId))
                })
            }
            composable(
                route = Screen.About.route
            ) {
                AboutScreen()
            }
            composable(
                route = Screen.NewsDetail.route,
                arguments = listOf(navArgument("newsId") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("newsId") ?: -1
                DetailScreen(
                    newsId = id,
                    navigateBack = { navController.navigateUp() },
                    openNews = { url ->
                        uriHandler.openUri(url)
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.home),
                icon = if (currentRoute == Screen.Home.route) Icons.Filled.Home else Icons.Outlined.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.about),
                icon = if (currentRoute == Screen.About.route) Icons.Filled.AccountCircle else Icons.Outlined.AccountCircle,
                screen = Screen.About
            )
        )
        BottomNavigation {
            navigationItem.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BeritaComposeAppPreview() {
    BeritaComposeTheme {
        BeritaComposeApp()
    }
}