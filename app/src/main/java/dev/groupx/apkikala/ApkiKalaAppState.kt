package dev.groupx.apkikala

import android.content.res.Resources
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager

class ApkiKalaAppState(
    val navController: NavHostController,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources
) {
    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }

}