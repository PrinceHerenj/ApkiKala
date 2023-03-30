package dev.groupx.apkikala.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.groupx.apkikala.ApkiKalaAppState
import dev.groupx.apkikala.ui.screen.egsplashscreen.SplashNode
import dev.groupx.apkikala.ui.screen.egsplashscreen.SplashScreen
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.screen.login.LoginScreen

fun NavGraphBuilder.apkiKalaGraph(appState: ApkiKalaAppState) {
    composable(SplashNode.route) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(LoginNode.route) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp)} )
    }
}


