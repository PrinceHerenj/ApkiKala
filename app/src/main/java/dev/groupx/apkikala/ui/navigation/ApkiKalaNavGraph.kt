package dev.groupx.apkikala.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.groupx.apkikala.ApkiKalaAppState
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.home.HomeScreen
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.screen.login.LoginScreen
import dev.groupx.apkikala.ui.screen.splashscreen.SplashNode
import dev.groupx.apkikala.ui.screen.splashscreen.SplashScreen

fun NavGraphBuilder.apkiKalaGraph(appState: ApkiKalaAppState) {
    composable(SplashNode.route) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(LoginNode.route) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp)} )
    }

    composable(HomeNode.route) {
        HomeScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp)},
            restartApp = {route -> appState.clearAndNavigate(route)},
            openScreen = {route -> appState.navigate(route)}
        )
    }

}


