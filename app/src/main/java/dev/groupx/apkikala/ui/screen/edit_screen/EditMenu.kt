package dev.groupx.apkikala.ui.screen.edit_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.common.composables.BasicButton
import dev.groupx.apkikala.ui.common.composables.CustomField
import dev.groupx.apkikala.ui.common.utils.basicButton
import dev.groupx.apkikala.ui.common.utils.fieldModifier
import dev.groupx.apkikala.ui.navigation.NavigationDestination

object EditNode : NavigationDestination {
    override val route = "EditMenu"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMenu(
    popUp: () -> Unit,
    openAndPopUp: (String, String) -> Unit,
    viewModel: EditViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    val fieldModifier = Modifier.fieldModifier()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(48.dp),
                title = { Text(text = "Update Details", Modifier.padding(top = 9.dp)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onBackClick(popUp) }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CustomField(
                text = "Username",
                value = uiState.username,
                onNewValue = viewModel::onUsernameChange,
                fieldModifier
            )
            CustomField(
                text = "Bio",
                value = uiState.bio,
                onNewValue = viewModel::onBioChange,
                fieldModifier
            )
            CustomField(
                text = "Address",
                value = uiState.address,
                onNewValue = viewModel::onAddressChange,
                fieldModifier
            )
            BasicButton(text = R.string.update_details, modifier = Modifier.basicButton()) {
                viewModel.onUpdateClick(openAndPopUp)
            }
        }
    }
}