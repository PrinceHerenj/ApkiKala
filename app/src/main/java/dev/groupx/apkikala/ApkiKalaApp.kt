package dev.groupx.apkikala

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.navigation.apkiKalaGraph
import dev.groupx.apkikala.ui.screen.egsplashscreen.SplashNode
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.theme.ApkiKalaTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun ApkiKalaApp() {
//    ApkiKalaTheme {
//        val appState = rememberAppState()
//
//        Scaffold(
//            snackbarHost = {
//                SnackbarHost(
//                    hostState = it,
//                    modifier = Modifier.padding(8.dp),
//                    snackbar = {snackbarData ->
//                        Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
//                    }
//                )
//            },
//            scaffoldState = appState.scaffoldState
//        ) { innerPaddingModifier ->
//            NavHost(
//                navController = appState.navController,
//                startDestination = SplashNode.route,
//                modifier = Modifier.padding(innerPaddingModifier)
//            ) {
//                apkiKalaGraph(appState)
//            }
//        }
//    }


}



@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        ApkiKalaAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
fun ApkiKalaTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    if (canNavigateBack) {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        )
    } else {
        TopAppBar(title = { Text(title) }, modifier = modifier)
    }
}