package dev.groupx.apkikala.ui.screen.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R.string as AppText
import dev.groupx.apkikala.ui.common.composables.*
import dev.groupx.apkikala.ui.common.utils.basicButton
import dev.groupx.apkikala.ui.common.utils.fieldModifier
import dev.groupx.apkikala.ui.navigation.NavigationDestination


object SignUpNode: NavigationDestination {
  override val route = "SignUpScreen"
  override val titleRes = AppText.app_name
}

@Composable
fun SignUpScreen(
  openScreen: (String) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SignUpViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState
  val fieldModifier = Modifier.fieldModifier()


  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CustomField("Username", uiState.username, viewModel::onUsernameChange, fieldModifier) // username
    CustomField("Tell us about yourself a little", uiState.bio, viewModel::onBioChange, fieldModifier) //
    CustomField("Address", uiState.address, viewModel::onAddressChange, fieldModifier)

    EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)

    PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
    RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

    BasicButton(AppText.add_profile_image, Modifier.basicButton()) {
      viewModel.onSignUpClick(openScreen)
    }
  }
}
