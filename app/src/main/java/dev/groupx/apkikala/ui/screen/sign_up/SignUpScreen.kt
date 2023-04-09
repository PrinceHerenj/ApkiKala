/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

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
import kotlinx.coroutines.launch

object SignUpNode: NavigationDestination {
  override val route = "SignUpScreen"
  override val titleRes = AppText.app_name
}

@Composable
fun SignUpScreen(
  openAndPopUp: (String, String) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SignUpViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState
  val fieldModifier = Modifier.fieldModifier()

  val coroutineScope = rememberCoroutineScope()

  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    EmailField(uiState.username, viewModel::onUsernameChange, fieldModifier) // username
    EmailField(uiState.bio, viewModel::onBioChange, fieldModifier) //
    EmailField(uiState.city, viewModel::onCityChange, fieldModifier) //
    EmailField(uiState.state, viewModel::onStateChange, fieldModifier) //
    EmailField(uiState.country, viewModel::onCountryChange, fieldModifier) //

    EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)

    PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
    RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

    BasicButton(AppText.create_account, Modifier.basicButton()) {
      viewModel.onSignUpClick(openAndPopUp)
    }
  }
}
