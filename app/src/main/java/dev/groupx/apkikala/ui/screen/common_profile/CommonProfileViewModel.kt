package dev.groupx.apkikala.ui.screen.common_profile

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CommonProfileViewModel @Inject constructor(
    accountService: AccountService,
    logService: LogService
): ApkiKalaViewModel(logService) {


    var accUiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    fun onBackClick(popUp: () -> Unit) = popUp()
}