package dev.groupx.apkikala.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.common.composables.BasicTextButton
import dev.groupx.apkikala.ui.common.composables.EmailField
import dev.groupx.apkikala.ui.common.composables.PasswordField
import dev.groupx.apkikala.ui.common.utils.basicButton
import dev.groupx.apkikala.ui.common.utils.fieldModifier
import dev.groupx.apkikala.ui.common.utils.textButton
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.R.string as AppText

object LoginNode: NavigationDestination {
    override val route = "LoginScreen"
    override val titleRes = AppText.app_name
}

@Composable
fun LoginScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.icons8_son_goku), contentDescription = null, modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(50))
        )
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())

        BasicButton(AppText.sign_in, Modifier.basicButton()) { viewModel.onSignInClick(openAndPopUp) }
        BasicButton(AppText.sign_up, Modifier.basicButton()) { viewModel.onSignUpClick(openScreen) }

        BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
            viewModel.onForgotPasswordClick()
        }
    }
}
