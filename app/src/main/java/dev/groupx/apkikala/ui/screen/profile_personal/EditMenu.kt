package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination

object EditNode : NavigationDestination {
    override val route = "EditMenu"
    override val titleRes = R.string.app_name
}

@Composable
fun Edit(){
    Text("Edit")
}