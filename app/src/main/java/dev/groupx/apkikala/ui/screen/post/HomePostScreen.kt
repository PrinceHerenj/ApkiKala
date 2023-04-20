package dev.groupx.apkikala.ui.screen.post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomePostScreen(
    modifier: Modifier = Modifier,
    viewModel: PostsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(uiState.posts) {post ->
            PostItem(post)
        }
    }
}