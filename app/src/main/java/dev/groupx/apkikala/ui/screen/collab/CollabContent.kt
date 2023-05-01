package dev.groupx.apkikala.ui.screen.collab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.model.ChatRoom
import dev.groupx.apkikala.ui.screen.common_chat.ChatRoomNode
import dev.groupx.apkikala.ui.screen.post.GetProfileImage
import dev.groupx.apkikala.ui.screen.post.ImageCommon
import dev.groupx.apkikala.ui.screen.post.LoadingScreen

@Composable
fun CollabContent(
    openScreen: (String) -> Unit,
    currentUserId: String,
    modifier: Modifier = Modifier,
    viewModel: CollabViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

//    LaunchedEffect(currentUserId) {
//        viewModel.getCurrentUserChatRooms(currentUserId)
//    }

    if (uiState.loading) {
        Box(modifier) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.7f))
            )
            LoadingScreen()
        }
    } else {
        when {
            uiState.chatRooms.isNotEmpty() -> {
                LazyColumn(Modifier.fillMaxWidth()) {
                    items(uiState.chatRooms) { chatRoom ->
                        ChatRoomItem(chatRoom, currentUserId, openScreen)
                    }
                }
            }
        }
    }
}

@Composable
fun ChatRoomItem(
    chatRoom: ChatRoom,
    currentUserId: String,
    openScreen: (String) -> Unit,
    viewModel: CollabViewModel = hiltViewModel(),
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickable {
                viewModel.onTopBarClick(openScreen, "${ChatRoomNode.route}/${currentUserId}/${chatRoom.receiver}")
            },
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GetProfileImage(
                profileImageUrl = chatRoom.profileImageUrl,
                createProfileImage = { imageUrl ->
                    ImageCommon(
                        imageUrl = imageUrl,
                        height = 48.dp,
                        width = 48.dp,
                        shape = RoundedCornerShape(50),
                        contentScale = ContentScale.Crop
                    )
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = chatRoom.username,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}