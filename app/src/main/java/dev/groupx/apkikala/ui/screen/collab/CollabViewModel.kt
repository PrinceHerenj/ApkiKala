package dev.groupx.apkikala.ui.screen.collab

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.profile_personal.PersonalProfileNode
import dev.groupx.apkikala.ui.screen.search.SearchNode
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CollabViewModel @Inject constructor(
    accountService: AccountService,
    logService: LogService
): ApkiKalaViewModel(logService)
{
    var uiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    fun onHomeClick(openAndPopUp: (String, String) -> Unit) = openAndPopUp(HomeNode.route, PersonalProfileNode.route)

    fun onSearchClick(openScreen: (String) -> Unit) = openScreen(SearchNode.route)

    fun onPersonalProfileClick(openScreen: (String) -> Unit) = openScreen(PersonalProfileNode.route)
}