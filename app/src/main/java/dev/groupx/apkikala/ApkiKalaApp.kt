package dev.groupx.apkikala

import android.content.res.Resources
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.navigation.apkiKalaGraph
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.egsplashscreen.SplashNode
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.theme.ApkiKalaTheme
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

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
    CText()

}

////// Test Block

@Composable
fun CText(viewModel: TempViewModel = hiltViewModel()) {
    val uiState by viewModel.tempUiState
    Row {
        TextField(value = uiState.url, onValueChange = viewModel::onURLChange)
        Button(onClick = { viewModel.onGet() }) {
        }
    }
}

@HiltViewModel
class TempViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
) : ApkiKalaViewModel(logService) {
     var tempUiState = mutableStateOf(TempUiState())
        private set

    private val url
        get() = tempUiState.value.url

    fun onURLChange(newValue: String) {
        tempUiState.value = tempUiState.value.copy(url = newValue)
    }

    fun onGet() {
        launchCatching {
            val s = storageService.loadImageURLFromFirestore()
            onURLChange(s)
        }
    }
}

data class TempUiState(
    val url: String = ""
)

/////


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