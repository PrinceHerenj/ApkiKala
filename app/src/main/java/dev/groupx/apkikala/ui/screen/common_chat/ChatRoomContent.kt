package dev.groupx.apkikala.ui.screen.common_chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R
import dev.groupx.apkikala.model.Chat
import dev.groupx.apkikala.ui.navigation.NavigationDestination
import dev.groupx.apkikala.ui.screen.AccountUiState
import dev.groupx.apkikala.ui.screen.post.LoadingScreen

object ChatRoomNode : NavigationDestination {
    override val route = "ChatRoomContent"
    override val titleRes = R.string.app_name
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomContent(
    sender: String,
    receiver: String,
    popUp: () -> Unit,
    viewModel: ChatRoomViewModel = hiltViewModel(),
) {
    val accUiState by viewModel.accUiState.collectAsState(initial = AccountUiState(false))
    val currentUserId = accUiState.currentUserId
    val uiState by viewModel.uiState.collectAsState()

    val scrollState = rememberLazyListState()

    LaunchedEffect(sender, receiver) {
//        viewModel.getChats(sender, receiver)
        scrollState.scrollToItem(uiState.chats.size - 1)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(48.dp),
                title = { Text(text = "") },
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
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            when {
                uiState.loading -> LoadingScreen()
                uiState.chats.isNotEmpty() -> {
                    LazyColumn(Modifier.fillMaxWidth(), state = scrollState) {
                        items(uiState.chats) { chat ->
                            if (chat.sender == currentUserId) {
                                Row() {
                                    Spacer(modifier = Modifier.fillMaxWidth(0.3f))
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp),
                                        Arrangement.End
                                    ) {
                                        ChatItem(chat)
                                    }
                                }

                            } else {
                                Row() {
                                    Row(
                                        Modifier
                                            .fillMaxWidth(0.7f)
                                            .padding(horizontal = 10.dp), Arrangement.Start
                                    ) {
                                        ChatItem(chat)
                                    }
                                    Spacer(modifier = Modifier.fillMaxWidth(0.3f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatItem(
    chat: Chat,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Box(
            Modifier
                .padding(20.dp),
        ) {
            Text(text = chat.chatContent, modifier = Modifier.align(Alignment.Center))
        }

    }


}
