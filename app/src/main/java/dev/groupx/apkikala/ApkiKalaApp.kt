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
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.home.HomeScreen
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.screen.splashscreen.SplashNode
import dev.groupx.apkikala.ui.theme.ApkiKalaTheme
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApkiKalaApp() {
//    ApkiKalaTheme {
//        val appState = rememberAppState()
//
//        Scaffold(
//            snackbarHost = {
////                SnackbarHost(
////                    hostState = it,
////                    modifier = Modifier.padding(8.dp),
////                    snackbar = {snackbarData ->
////                        Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary)
////                    }
////                )
//                SnackbarHost(appState.scaffoldState)
//
//            },
////            scaffoldState = appState.scaffoldState
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

//    CText()

    HomeScreen()

}

//////// Test Block
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CText(viewModel: TempViewModel = hiltViewModel()) {
//    val uiState by viewModel.tempUiState
//    Row {
//        TextField(value = uiState.url, onValueChange = viewModel::onURLChange)
//        Button(onClick = { viewModel.onGet() }) {
//        }
//
//    }
//}
//
//@HiltViewModel
//class TempViewModel @Inject constructor(
//    private val storageService: StorageService,
//    logService: LogService
//) : ApkiKalaViewModel(logService) {
//     var tempUiState = mutableStateOf(TempUiState())
//        private set
//
//    private val url
//        get() = tempUiState.value.url
//
//    fun onURLChange(newValue: String) {
//        tempUiState.value = tempUiState.value.copy(url = newValue)
//    }
//
//    fun onGet() {
//        launchCatching {
//            val s = storageService.loadImageURLFromFirestore()
//            onURLChange(s)
//        }
//    }
//}
//
//data class TempUiState(
//    val url: String = ""
//)
//
///////


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

//@Composable
//fun ApkiKalaTopAppBar(
//    title: String,
//    canNavigateBack: Boolean,
//    modifier: Modifier = Modifier,
//    navigateUp: () -> Unit = {}
//) {
//    if (canNavigateBack) {
//        TopAppBar(
//            title = { Text(title) },
//            modifier = modifier,
//            navigationIcon = {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Filled.ArrowBack,
//                        contentDescription = stringResource(R.string.back_button)
//                    )
//                }
//            }
//        )
//    } else {
//        TopAppBar(title = { Text(title) }, modifier = modifier)
//    }
//}