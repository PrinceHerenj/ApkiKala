package dev.groupx.apkikala.ui.screen.search

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.common.composables.CustomField
import dev.groupx.apkikala.ui.common.utils.fieldModifier
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.ui.screen.post.LoadingScreen
import dev.groupx.apkikala.R.string as AppText
import com.example.answerandquestion.loginactivity


object SearchNode : NavigationDestination {
    override val route = "SearchScreen"
    override val titleRes = AppText.app_name
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(48.dp),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.apki_kala_logo_hs_final_centered),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(vertical = 1.dp)
                            .size(48.dp)
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { viewModel.onHomeClick(openAndPopUp) },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Filled.Search, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        val intent = Intent(context, loginactivity::class.java)
                        context.startActivity(intent)
                    },
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        viewModel.onPersonalProfileClick(openScreen)
                    },
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) }
                )
            }
        }) {
        Column(
            Modifier
                .padding(it)
                .padding(top = 24.dp)) {
            CustomField(
                text = "Search",
                value = uiState.searchString,
                onNewValue = viewModel::onSearchStringChange,
                Modifier.fieldModifier()
            )
            Spacer(modifier = Modifier.size(16.dp))

            when {
                uiState.loading -> LoadingScreen()
                uiState.SearchResults.isNotEmpty() -> {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(uiState.SearchResults) { searchResult ->
                            SearchItem(searchResult, openAndPopUp)
                        }
                    }
                }
            }

        }
    }
}