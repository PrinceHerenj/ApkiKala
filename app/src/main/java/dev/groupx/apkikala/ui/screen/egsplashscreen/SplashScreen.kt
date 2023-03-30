package dev.groupx.apkikala.ui.screen.egsplashscreen

import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R.string as AppText
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.common.utils.basicButton
import kotlinx.coroutines.delay

object SplashNode : NavigationDestination {
    override val route = "SplashScreen"
    override val titleRes = R.string.app_name
}

private const val SPLASH_TIMEOUT = 5000L

@Composable
fun SplashScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {
    Column(
        modifier =
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.showError.value) {
            Text(text = stringResource(AppText.generic_error))

            BasicButton(AppText.try_again, Modifier.basicButton()) {
                viewModel.onAppStart(
                    openAndPopUp
                )
            }
        } else {
            CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
        }
    }

    LaunchedEffect(true) {
        delay(SPLASH_TIMEOUT)
        viewModel.onAppStart(openAndPopUp)
    }
}
