package dev.groupx.apkikala.ui.screen.home

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.StorageService
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    private val storageService: StorageService
//): ViewModel() {
//
//
//}