package dev.groupx.apkikala.ui.screen.like_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.navigation.NavigationDestination

object CommonLikeNode : NavigationDestination {
    override val route = "CommonLikeScreen"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonLikeScreen(
    postId: String,
    popUp: () -> Unit,
    viewModel: LikesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getLikes(postId)
    }

    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(48.dp),
                title = { Text(text = "Likes", Modifier.padding(top = 9.dp)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onBackClick(popUp) }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
            )
        }
    )     {
        Box(modifier = Modifier
            .padding(it)
            .padding(top = 24.dp)) {
            when {
                uiState.likes.isNotEmpty() -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(uiState.likes) { like ->
                            LikeItem(like)
                        }
                    }
                }
            }
        }
    }
}

