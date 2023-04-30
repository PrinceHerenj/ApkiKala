package dev.groupx.apkikala.ui.screen.profile_personal

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.collab.CollabNode
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.login.LoginNode
import dev.groupx.apkikala.ui.screen.search.SearchNode
import dev.groupx.apkikala.ui.screen.sign_up.SignUpNode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PersonalProfileViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var accUiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    var uiState = MutableStateFlow(PersonalProfileUiState())
        private set


    fun getDetails(userId: String) {
        launchCatching {
            Log.d("Here", userId)
            uiState.value = uiState.value.copy(loading = true)
            try {
                val profile = withContext(Dispatchers.IO) {
                    storageService.getProfile(userId)
                }
                uiState.value = uiState.value.copy(loading = false, profile = profile)
            } catch (e: Exception) {
                Log.d("Here", e.toString())
            }
        }
    }

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LoginNode.route)
    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SignUpNode.route)

    fun onSignOutClick(clearAndNavigate: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            clearAndNavigate(LoginNode.route)
        }
    }

    fun onDeleteAccClick(clearAndNavigate: (String) -> Unit, id: String) {
        launchCatching {
            accountService.deleteAccount(id)
            clearAndNavigate(LoginNode.route)
        }
    }

    fun onHomeClick(openAndPopUp: (String, String) -> Unit) = openAndPopUp(HomeNode.route, PersonalProfileNode.route)
    fun onSearchClick(openScreen: (String) -> Unit) = openScreen(SearchNode.route)
    fun onCollabClick(openScreen: (String) -> Unit) = openScreen(CollabNode.route)
    fun onBackClick(popUp: () -> Unit) = popUp()
    fun onEditClick(openScreen: (String) -> Unit) = openScreen(EditNode.route)



}
