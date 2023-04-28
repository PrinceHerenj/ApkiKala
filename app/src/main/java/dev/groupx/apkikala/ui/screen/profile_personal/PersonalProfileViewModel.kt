package dev.groupx.apkikala.ui.screen.profile_personal

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.collab.CollabNode
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.screen.search.SearchNode
import dev.groupx.apkikala.ui.screen.sign_up.SignUpNode
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PersonalProfileViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var uiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LoginNode.route)
    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SignUpNode.route)

    fun onSignOutClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.signOut()
            openAndPopUp(LoginNode.route, PersonalProfileNode.route)
        }
    }

    fun onDeleteAccClick(openAndPopUp: (String, String) -> Unit, id: String) {
        launchCatching {
            accountService.deleteAccount(id)
            openAndPopUp(LoginNode.route, HomeNode.route)
        }
    }

    fun onHomeClick(openAndPopUp: (String, String) -> Unit) = openAndPopUp(HomeNode.route, PersonalProfileNode.route)
    fun onSearchClick(openScreen: (String) -> Unit) = openScreen(SearchNode.route)
    fun onCollabClick(openScreen: (String) -> Unit) = openScreen(CollabNode.route)
    fun onBackClick(popUp: () -> Unit) = popUp()
}