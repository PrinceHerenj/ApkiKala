package dev.groupx.apkikala.ui.screen.login

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.common.utils.isValidEmail
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.sign_up.SignUpNode
import javax.inject.Inject
import dev.groupx.apkikala.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : ApkiKalaViewModel(logService) {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        launchCatching {
            accountService.deleteAccount(accountService.currentUserId)
            accountService.authenticate(email, password)
            openAndPopUp(HomeNode.route, LoginNode.route)
        }
    }

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SignUpNode.route)

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }

    }


}