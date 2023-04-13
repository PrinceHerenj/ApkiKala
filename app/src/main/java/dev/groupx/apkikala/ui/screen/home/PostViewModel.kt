package dev.groupx.apkikala.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.StorageService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val storageService: StorageService
) :ViewModel() {
    var profileImageUrlState by mutableStateOf("")

    var postImageUrlState by mutableStateOf("")

    init {
        getProfileImageState()
    }

    private fun getProfileImageState() = viewModelScope.launch {
        profileImageUrlState = storageService.loadImageURLFromFirestore()
        postImageUrlState = storageService.loadImageURLFromFirestore()
    }
}