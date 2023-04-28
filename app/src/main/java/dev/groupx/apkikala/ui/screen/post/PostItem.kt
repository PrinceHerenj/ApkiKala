package dev.groupx.apkikala.ui.screen.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.Timestamp
import dev.groupx.apkikala.model.Post
import dev.groupx.apkikala.ui.screen.AccountUiState
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun PostItem(
    post: Post,
    openScreen: (String) -> Unit,
    viewModel: PostsViewModel = hiltViewModel(),
) {
    val accUiState by viewModel.accUiState.collectAsState(initial = AccountUiState(false))

    val uiState = remember {
        mutableStateOf(post)
    }

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PostTopBar(
                uiState.value.user,
                uiState.value.profileImageUrl,
                uiState.value.username,
                openScreen
            )
            PostContent(uiState.value.postImageUrl)
            if (!accUiState.isAnonymousAccount) {
                PostBottomBar(
                    uiState,
                    { viewModel.likePost(uiState.value.postId) },
                    { viewModel.dislikePost(uiState.value.postId) },
                    { viewModel.onViewCommentsClick(openScreen, postId = uiState.value.postId) }
                )
            } else {
                PostBottomBar(
                    uiState,
                    { viewModel.showAnonymousError() },
                    { viewModel.showAnonymousError() },
                    { viewModel.showAnonymousError() }
                )
            }

        }
    }

    PostDescription(
        uiState,
        uiState.value.title,
        timestampToString(uiState.value.createdAt, "MMMM dd"),
        uiState.value.description
    )

}


@Composable
fun PostTopBar(
    uid: String,
    profileImageUrl: String,
    username: String,
    openScreen: (String) -> Unit,
    viewModel: PostsViewModel = hiltViewModel(),
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .padding(horizontal = 8.dp)
            .clickable { viewModel.onTopBarClick(openScreen, uid) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GetProfileImage(
            profileImageUrl = profileImageUrl,
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
            text = username,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.MoreVert, contentDescription = null)
        }
    }
}

@Composable
fun PostContent(postImageUrl: String) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .padding(0.dp)
    ) {

        GetPostImage(
            postImageUrl = postImageUrl,
            createPostImage = { imageUrl ->
                ImageCommon(
                    imageUrl = imageUrl,
                    height = 400.dp,
                    width = 400.dp,
                    shape = RoundedCornerShape(0.dp),
                    contentScale = ContentScale.FillWidth,
                )
            }
        )

    }
}

@Composable
fun PostBottomBar(
    uiState: MutableState<Post>,
    actionOnLike: () -> Unit,
    actionOnDislike: () -> Unit,
    actionOnComment: () -> Unit,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .height(48.dp)
    ) {
        val likedByCurrentUser = uiState.value.likedByCurrentUser
        val currentLikes = uiState.value.likes

        if (!likedByCurrentUser) {
            IconButton(onClick = { uiState.value = uiState.value.copy(likedByCurrentUser = true, likes = (currentLikes+1)) ; actionOnLike() }) {
                Icon(Icons.Filled.FavoriteBorder, contentDescription = null)
            }
        } else {
            IconButton(onClick = { uiState.value = uiState.value.copy(likedByCurrentUser = false, likes = (currentLikes-1)); actionOnDislike() }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { actionOnComment() }) {
            Icon(Icons.Outlined.Article, contentDescription = null)
        }
    }
}

@Composable
fun PostDescription(
    uiState: MutableState<Post>,
    title: String,
    createdAt: String,
    description: String,
) {
    val likes = uiState.value.likes
    Column(
        Modifier
            .padding(horizontal = 16.dp)
//            .padding(bottom = 16.dp)
    ) {
        if (likes == 1) {
            Text(text = "$likes like")
        } else {
            Text(text = "$likes likes")
        }
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = createdAt)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 10.dp)
        )

    }
}


fun timestampToString(timestamp: Timestamp, format: String): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    val date = timestamp.toDate()
    return sdf.format(date)
}

@Composable
fun GetPostImage(
    postImageUrl: String,
    createPostImage: @Composable (imageUrl: String) -> Unit,
) {
    createPostImage(postImageUrl)
}

@Composable
fun GetProfileImage(
    profileImageUrl: String,
    createProfileImage: @Composable (imageUrl: String) -> Unit,
) {
    createProfileImage(profileImageUrl)
}

@Composable
fun ImageCommon(
    imageUrl: String,
    height: Dp,
    width: Dp,
    shape: Shape,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = contentScale,
            modifier = Modifier
                .clip(shape)
                .width(width)
                .height(height)
        )
    }
}

