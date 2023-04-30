package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.ui.common.composables.ProfileDescription
import dev.groupx.apkikala.ui.common.composables.Stats
import dev.groupx.apkikala.ui.screen.post.GetProfileImage
import dev.groupx.apkikala.ui.screen.post.ImageCommon
import dev.groupx.apkikala.ui.screen.post.PostItem


@Composable
fun ProfileSection(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    userId: String,
    viewModel: PersonalProfileViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.getDetailsAndProfilePosts(userId)
    }

    if (uiState.loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.7f))
        )
    } else {
        Column(
            modifier = modifier
        ) {
            when {
                uiState.posts.isNotEmpty() -> {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        item {
                            ElevatedCard(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                ),
                                shape = RoundedCornerShape(4.dp),
                                elevation = CardDefaults.cardElevation(
                                    4.dp
                                ),
                                modifier = Modifier.padding(10.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 24.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    GetProfileImage(profileImageUrl = uiState.profile.profileImageUrl) {
                                        ImageCommon(
                                            imageUrl = it,
                                            height = 120.dp,
                                            width = 120.dp,
                                            shape = CircleShape,
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Stats(numberText = uiState.profile.posts.toString(), text = "Posts")
                                    Stats(numberText = uiState.profile.followers.toString(), text = "Followers")
                                    Stats(numberText = uiState.profile.following.toString(), text = "Following")
                                }

                                ProfileDescription(
                                    displayName = uiState.profile.username,
                                    bio = uiState.profile.bio,
                                    address = uiState.profile.address,
                                )
                            }
                        }
                        items(uiState.posts) {post ->
                            PostItem(post = post, openScreen = openScreen)
                        }
                    }
                }
            }

        }
    }
}

