package dev.groupx.apkikala.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.StorageService
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val storageService: StorageService
) :ViewModel() {
    var profileImageUrlState by mutableStateOf("https://firebasestorage.googleapis.com/v0/b/cloudstoragee-41a14.appspot.com/o/images%2FA23Hr7F2.jpg?alt=media&token=fa82a414-c11a-4f9f-bd44-2d85bd65284a")

    var postImageUrlState by mutableStateOf("https://firebasestorage.googleapis.com/v0/b/cloudstoragee-41a14.appspot.com/o/images%2FA23Hr7F2.jpg?alt=media&token=fa82a414-c11a-4f9f-bd44-2d85bd65284a")

//    init {
//        getProfileImageState()
//    }
//
//    private fun getProfileImageState() = viewModelScope.launch {
//        profileImageUrlState = storageService.loadImageURLFromFirestore()
//    }
}