package dev.groupx.apkikala.ui.screen.home

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.collab.CollabNode
import dev.groupx.apkikala.ui.screen.create_post.CreatePostNode
import dev.groupx.apkikala.ui.screen.profile_personal.PersonalProfileNode
import dev.groupx.apkikala.ui.screen.search.SearchNode
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    accountService: AccountService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {
    var accUiState = accountService.currentUser.map { AccountUiState(it.isAnonymous, it.id) }
        private set

    fun onHomeClick(openAndPopUp: (String, String) -> Unit) = openAndPopUp(HomeNode.route, HomeNode.route)
    fun onSearchClick(openScreen: (String) -> Unit) = openScreen(SearchNode.route)

    fun onCollabClick(openScreen: (String) -> Unit) = openScreen(CollabNode.route)

    fun onPersonalProfileClick(openScreen: (String) -> Unit) = openScreen(PersonalProfileNode.route)

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(CreatePostNode.route)

    fun showAnonymousError() = SnackbarManager.showMessage(R.string.Anonym_Error)

}