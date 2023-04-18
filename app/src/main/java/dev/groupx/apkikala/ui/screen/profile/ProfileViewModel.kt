package dev.groupx.apkikala.ui.screen.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.groupx.apkikala.model.service.LogService
import dev.groupx.apkikala.ui.screen.ApkiKalaViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    logService: LogService,
) : ApkiKalaViewModel(logService) {
}