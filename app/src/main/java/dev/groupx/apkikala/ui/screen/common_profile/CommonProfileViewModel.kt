package dev.groupx.apkikala.ui.screen.common_profile

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.Profile
import dev.groupx.apkikala.model.service.AccountService
import dev.groupx.apkikala.model.service.ChatService
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.model.service.StorageService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import dev.groupx.apkikala.ui.screen.common_chat.ChatRoomNode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CommonProfileViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService,
    private val chatService: ChatService,
    logService: LogService,
) : ApkiKalaViewModel(logService) {


    var uiState = MutableStateFlow(CommonProfileUiState())
        private set

    private val userId
        get() = uiState.value.profile.userId

    private val username
        get() = uiState.value.profile.username
    private val profileImageUrl
        get() = uiState.value.profile.profileImageUrl
    private val address
        get() = uiState.value.profile.address
    private val bio
        get() = uiState.value.profile.bio
    private val following
        get() = uiState.value.profile.following
    private val followers
        get() = uiState.value.profile.followers

    private val posts
        get() = uiState.value.profile.posts


    fun getDetails(userId: String) {
        launchCatching {
            uiState.value = uiState.value.copy(loading = true)
            try {
                val profile = withContext(Dispatchers.IO) {
                    storageService.getProfile(userId)
                }
                val posts = withContext(Dispatchers.IO) {
                    storageService.getFeedPostsFiltered(userId)
                }
                if (profile.userId == accountService.currentUserId) {
                    uiState.value = uiState.value.copy(
                        loading = false,
                        profile = profile,
                        isCurrentUserProfile = true,
                        posts = posts
                    )
                }


                val following = withContext(Dispatchers.IO) {
                    storageService.isFollowedBy(accountService.currentUserId, userId)
                }
                uiState.value = uiState.value.copy(
                    loading = false,
                    profile = profile,
                    following = following,
                    posts = posts
                )
            } catch (e: Exception) {
                Log.d("Here", e.toString())
            }
        }
    }

    fun onBackClick(popUp: () -> Unit) = popUp()
    fun onFollowClick() {
        launchCatching {
            val newFollowers = followers + 1
            storageService.addFollower(accountService.currentUserId, userId, newFollowers)
            uiState.value = uiState.value.copy(
                profile = Profile(
                    userId, username, profileImageUrl, address, bio, newFollowers, following, posts
                ),
                following = true
            )
        }

    }

    fun onUnFollowClick() {
        launchCatching {
            val newFollowers = followers - 1
            storageService.removeFollower(accountService.currentUserId, userId, newFollowers)
            uiState.value = uiState.value.copy(
                profile = Profile(
                    userId, username, profileImageUrl, address, bio, newFollowers, following, posts
                ),
                following = false
            )
        }
    }

    fun onCollabClick(openScreen: (String) -> Unit, collabWithUserId: String) {
        launchCatching {
            val currentUser = accountService.currentUserId
            if (!chatService.checkChatRooms(currentUser, collabWithUserId)) {
                chatService.createChatRooms(currentUser, collabWithUserId)
            }
            openScreen("${ChatRoomNode.route}/${currentUser}/${collabWithUserId}")
        }
    }
}