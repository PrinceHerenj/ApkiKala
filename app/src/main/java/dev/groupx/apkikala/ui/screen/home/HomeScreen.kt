package dev.groupx.apkikala.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.R.string as AppText


object HomeNode : NavigationDestination {
    override val route = "HomeScreen"
    override val titleRes = AppText.app_name
}

@Composable
fun HomeScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
//    openAndPopUp: (String, String) -> Unit,
//    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = HomeUiState(false, ""))

    Column {
        Post()
        Spacer(modifier = Modifier.height(200.dp))

        if (uiState.isAnonymousAccount) {
            BasicButton(AppText.sign_in, modifier = Modifier.padding()) {
                viewModel.onLoginClick(openScreen)
            }
        } else {
            BasicButton(AppText.sign_out, modifier = Modifier.padding()) {
                viewModel.onSignOutClick(restartApp)
            }
        }
        Text(text = uiState.currentUserId)

    }

}

