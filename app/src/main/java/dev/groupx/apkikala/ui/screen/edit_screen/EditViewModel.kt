package dev.groupx.apkikala.ui.screen.edit_screen

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.profile_personal.PersonalProfileNode
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {

    var uiState = mutableStateOf(EditUiState())
        private set

    private val username
        get() = uiState.value.username

    private val bio
        get() = uiState.value.bio

    private val address
        get() = uiState.value.address

    init {
        getCurrentDetails()
    }

    private fun getCurrentDetails() {
        launchCatching {
            val currentUserId = accountService.currentUserId
            val details: List<String> = storageService.getCurrentUserDetails(currentUserId)

            uiState.value = uiState.value.copy(
                username = details[0],
                bio = details[1],
                address = details[2]
            )
        }
    }

    fun onUpdateClick(openAndPopUp: (String, String)-> Unit) {
        launchCatching {
            storageService.setUserDetails(accountService.currentUserId, username, bio, address)
            openAndPopUp(PersonalProfileNode.route, EditNode.route)
        }
    }

    fun onBackClick(popUp: () -> Unit) = popUp()
    fun onAddressChange(s: String) {
        uiState.value = uiState.value.copy(address = s)
    }

    fun onBioChange(s: String) {
        uiState.value = uiState.value.copy(bio = s)
    }

    fun onUsernameChange(s: String) {
        uiState.value = uiState.value.copy(username = s)
    }
}