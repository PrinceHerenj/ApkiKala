package dev.groupx.apkikala.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.groupx.apkikala.ApkiKalaAppState
import dev.groupx.apkikala.ui.screen.collab.CollabNode
import dev.groupx.apkikala.ui.screen.collab.CollabScreen
import dev.groupx.apkikala.ui.screen.create_post.CreatePostNode
import dev.groupx.apkikala.ui.screen.create_post.CreatePostScreen
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.home.HomeScreen
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.screen.login.LoginScreen
import dev.groupx.apkikala.ui.screen.profile_personal.PersonalProfileNode
import dev.groupx.apkikala.ui.screen.profile_personal.PersonalProfileScreen
import dev.groupx.apkikala.ui.screen.search.SearchNode
import dev.groupx.apkikala.ui.screen.search.SearchScreen
import dev.groupx.apkikala.ui.screen.sign_up.SignUpNode
import dev.groupx.apkikala.ui.screen.sign_up.SignUpScreen
import dev.groupx.apkikala.ui.screen.splashscreen.SplashNode
import dev.groupx.apkikala.ui.screen.splashscreen.SplashScreen

fun NavGraphBuilder.apkiKalaGraph(appState: ApkiKalaAppState) {
    composable(SplashNode.route) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(LoginNode.route) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(HomeNode.route) {
        HomeScreen(
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(SignUpNode.route) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(PersonalProfileNode.route) {
        PersonalProfileScreen(
            openScreen = { route -> appState.navigate(route) },
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
            restartApp = { route -> appState.clearAndNavigate(route) }
        )
    }

    composable(CreatePostNode.route) {
        CreatePostScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SearchNode.route) {
        SearchScreen()
    }

    composable(CollabNode.route) {
        CollabScreen()
    }


}


