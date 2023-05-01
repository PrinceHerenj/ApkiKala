package dev.groupx.apkikala

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.navigation.apkiKalaGraph
import dev.groupx.apkikala.ui.screen.splashscreen.SplashNode
import dev.groupx.apkikala.ui.theme.ApkiKalaTheme
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApkiKalaApp() {
    ApkiKalaTheme {
        val appState = rememberAppState()

        Scaffold(
            snackbarHost = {
                SnackbarHost(appState.scaffoldState)

            },
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = SplashNode.route,
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                apkiKalaGraph(appState)
            }
        }
    }


}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Composable
fun rememberAppState(
    scaffoldState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        ApkiKalaAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

