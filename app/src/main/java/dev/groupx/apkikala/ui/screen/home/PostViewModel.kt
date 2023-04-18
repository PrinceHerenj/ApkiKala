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
        getPostImageStates()
    }

    private fun getPostImageStates() = viewModelScope.launch {
        profileImageUrlState = storageService.loadImageURLFromFirestore("5dVVboSdA2JDcF17RAC2")
        postImageUrlState = storageService.loadImageURLFromFirestore("5dVVboSdA2JDcF17RAC2")
    }
}