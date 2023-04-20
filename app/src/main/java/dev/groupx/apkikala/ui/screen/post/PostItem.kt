package dev.groupx.apkikala.ui.screen.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.groupx.apkikala.model.Post


@Composable
fun PostItem(post: Post) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PostTopBar(
                post.profileImageUrl,
                post.username
            )
            PostContent(post.postImageUrl)
            PostBottomBar()
        }
    }

    PostDescription(
        post.title,
        "19th April",
        post.description
    )
}

@Composable
fun PostDescription(
    title: String,
    createdAt: String,
    description: String,
) {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Row {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = createdAt, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall
        )

    }
}

@Composable
fun PostTopBar(profileImageUrl: String, username: String) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp)
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 0.dp)
            .padding(horizontal = 0.dp)
    ) {
        GetPostImage(
            postImageUrl = postImageUrl,
            createPostImage = { imageUrl ->
                ImageCommon(
                    imageUrl = imageUrl,
                    height = 400.dp,
                    width = 400.dp,
                    shape = RoundedCornerShape(4.dp),
                    contentScale = ContentScale.FillWidth,
//                    modifier = Modifier
//                        .clickable {  }
                )
            }
        )
    }
}

@Composable
fun PostBottomBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .height(48.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Notifications, contentDescription = null)
        }
    }
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

