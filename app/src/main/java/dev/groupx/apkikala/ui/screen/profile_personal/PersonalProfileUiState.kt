package dev.groupx.apkikala.ui.screen.profile_personal

import dev.groupx.apkikala.model.Profile

data class PersonalProfileUiState(
    val loading: Boolean = false,
    val profile: Profile = Profile(),
)