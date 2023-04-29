package dev.groupx.apkikala.ui.screen.post

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.ui.common.snackbar.SnackbarManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomePostScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PostsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        viewModel.getPosts()
        delay(1500)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    LaunchedEffect(Unit) {
        viewModel.getPosts()
    }

    when {
        uiState.loading -> PostLoadingScreen(modifier)
        uiState.posts.isNotEmpty() -> {
            var canHandleBackButton by remember {
                mutableStateOf(true)
            }
            Box(modifier.pullRefresh(state)) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.posts) { post ->
                        PostItem(post, openScreen)
                    }
                }
                PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                BackHandler(enabled = canHandleBackButton) {
                    canHandleBackButton = false
                    SnackbarManager.showMessage(R.string.back_again_to_exit)
                }
            }
        }
    }


}

@Composable
fun PostLoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            DefaultPostItem()
            DefaultPostItem()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.7f))
        )

//        CircularProgressIndicator()
    }

}

@Composable
fun LoadingScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }

}
