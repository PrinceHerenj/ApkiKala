package dev.groupx.apkikala.ui.screen.sign_up

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.common.utils.isValidEmail
import dev.groupx.apkikala.ui.common.utils.isValidPassword
import dev.groupx.apkikala.ui.common.utils.passwordMatches
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import javax.inject.Inject
import dev.groupx.apkikala.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val accountService: AccountService,
  logService: LogService
) : ApkiKalaViewModel(logService) {
  var uiState = mutableStateOf(SignUpUiState())
    private set

  private val username
    get() = uiState.value.username

  private val bio
    get() = uiState.value.bio

  private val address
    get() = uiState.value.address

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password


  fun onUsernameChange(newValue: String) {
    uiState.value = uiState.value.copy(username = newValue)
  }

  fun onBioChange(newValue: String) {
    uiState.value = uiState.value.copy(bio = newValue)
  }

  fun onAddressChange(newValue: String) {
    uiState.value = uiState.value.copy(address = newValue)
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

  fun onSignUpClick(openScreen: (String) -> Unit) {
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
      accountService.linkAccount(email, password, username, address, bio)
      openScreen(UploadProfileImageNode.route)
    }
  }

}


