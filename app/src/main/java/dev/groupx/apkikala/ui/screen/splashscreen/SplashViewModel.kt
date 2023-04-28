package dev.groupx.apkikala.ui.screen.splashscreen

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.ConfigurationService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.login.LoginNode
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    val showError = mutableStateOf(false)

    var uiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    init {
        launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit, uiState: AccountUiState) {
        showError.value = false
        if (accountService.hasUser) {
            if (!uiState.isAnonymousAccount) openAndPopUp(HomeNode.route, SplashNode.route)
            else openAndPopUp(LoginNode.route, SplashNode.route)
        }
        else {
            openAndPopUp(LoginNode.route, HomeNode.route)
            createAnonymousAccount()
        }
    }


    private fun createAnonymousAccount() {
        launchCatching(snackbar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
        }
    }
}