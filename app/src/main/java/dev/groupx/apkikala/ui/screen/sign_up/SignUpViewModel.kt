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

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.common.utils.isValidEmail
import dev.groupx.apkikala.ui.common.utils.isValidPassword
import dev.groupx.apkikala.ui.common.utils.passwordMatches
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.home.HomeNode
import javax.inject.Inject
import dev.groupx.apkikala.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val accountService: AccountService,
  private val storageService: StorageService,
  logService: LogService
) : ApkiKalaViewModel(logService) {
  var uiState = mutableStateOf(SignUpUiState())
    private set

  private val username
    get() = uiState.value.username
  private val bio
    get() = uiState.value.bio
  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password
  private val city
    get() = uiState.value.city
  private val state
    get() = uiState.value.state
  private val country
    get() = uiState.value.country

  fun onUsernameChange(newValue: String) {
    uiState.value = uiState.value.copy(username = newValue)
  }

  fun onBioChange(newValue: String) {
    uiState.value = uiState.value.copy(bio = newValue)
  }

  fun onCityChange(newValue: String) {
    uiState.value = uiState.value.copy(city = newValue)
  }

  fun onStateChange(newValue: String) {
    uiState.value = uiState.value.copy(state = newValue)
  }

  fun onCountryChange(newValue: String) {
    uiState.value = uiState.value.copy(country = newValue)
  }

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }

  fun onRepeatPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(repeatPassword = newValue)
  }

  fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    if (!password.isValidPassword()) {
      SnackbarManager.showMessage(AppText.password_error)
      return
    }

    if (!password.passwordMatches(uiState.value.repeatPassword)) {
      SnackbarManager.showMessage(AppText.password_match_error)
      return
    }

    launchCatching {
      accountService.linkAccount(email, password)
      openAndPopUp(HomeNode.route, SignUpNode.route)
    }
  }

}


