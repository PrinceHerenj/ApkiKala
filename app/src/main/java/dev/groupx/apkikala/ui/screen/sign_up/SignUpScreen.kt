package dev.groupx.apkikala.ui.screen.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
  popUp: () -> Unit,
  openScreen: (String) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SignUpViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState
  val fieldModifier = Modifier.fieldModifier()
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        modifier = Modifier.height(48.dp),
        title = { Text(text = "Create an Account", Modifier.padding(top = 9.dp)) },
        navigationIcon = {
          IconButton(onClick = { viewModel.onBackClick(popUp) }) {
            Icon(
              Icons.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.Black,
              modifier = Modifier.size(25.dp)
            )
          }
        }
      )
    }
  ) {

    Column(
      modifier = modifier
        .padding(it)
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState())
        .padding(top = 16.dp),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      CustomField(
        "Username",
        uiState.username,
        viewModel::onUsernameChange,
        fieldModifier
      ) // username
      CustomField(
        "Tell us about yourself a little",
        uiState.bio,
        viewModel::onBioChange,
        fieldModifier
      ) //
      CustomField("Address", uiState.address, viewModel::onAddressChange, fieldModifier)

      EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)

      PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
      RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

      BasicButton(AppText.add_profile_image, Modifier.basicButton()) {
        viewModel.onSignUpClick(openScreen)
      }
    }
  }
}
