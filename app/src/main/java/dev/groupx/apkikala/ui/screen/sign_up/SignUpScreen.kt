package dev.groupx.apkikala.ui.screen.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.common.composables.CustomField
import dev.groupx.apkikala.ui.common.composables.EmailField
import dev.groupx.apkikala.ui.common.composables.PasswordField
import dev.groupx.apkikala.ui.common.composables.RepeatPasswordField
import dev.groupx.apkikala.ui.common.utils.basicButton
import dev.groupx.apkikala.ui.common.utils.fieldModifier
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.R.string as AppText


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
      .verticalScroll(rememberScrollState())
      .padding(top = 16.dp),
    verticalArrangement = Arrangement.Top,
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
