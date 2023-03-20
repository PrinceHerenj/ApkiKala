package dev.groupx.apkikala.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.groupx.apkikala.ui.screen.home.HomeNode

@Composable
fun ApkiKalaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeNode.route,
        modifier = modifier
    ) {
        composable(route = HomeNode.route) {

        }
    }
}