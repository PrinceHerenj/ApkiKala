package dev.groupx.apkikala.ui.screen.search

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.collab.CollabNode
import dev.groupx.apkikala.ui.screen.common_profile.CommonProfileNode
import dev.groupx.apkikala.ui.screen.home.HomeNode
import dev.groupx.apkikala.ui.screen.profile_personal.PersonalProfileNode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {

    var uiState = MutableStateFlow(SearchResultsUiState())
        private set

    private val searchString
        get() = uiState.value.searchString

    fun onHomeClick(openAndPopUp: (String, String) -> Unit) =
        openAndPopUp(HomeNode.route, PersonalProfileNode.route)

    fun onPersonalProfileClick(openScreen: (String) -> Unit) = openScreen(PersonalProfileNode.route)
    fun onCollabClick(openScreen: (String) -> Unit) = openScreen(CollabNode.route)
    fun onSearchStringChange(newValue: String) {
        launchCatching {
            uiState.value = uiState.value.copy(searchString = newValue, loading = true)
            if (searchString == "")
                uiState.value = uiState.value.copy(searchString = "", loading = false, SearchResults = emptyList())
            else {
                try {
                    val searchResults = withContext(Dispatchers.IO) {
                        Log.d("here", searchString)
                        storageService.getSearchResults(searchString)
                    }
                    uiState.value =
                        uiState.value.copy(loading = false, SearchResults = searchResults)
                } catch (e: Exception) {
                    Log.d("here", e.toString())
                }
            }
        }
    }

    fun onSearchItemClick(openAndPopUp: (String, String) -> Unit, user: String) {
        val newRoute = "${CommonProfileNode.route}/$user"
        openAndPopUp(newRoute, SearchNode.route)
    }


}