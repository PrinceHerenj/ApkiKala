package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import dev.groupx.apkikala.ui.screen.post.GetProfileImage
import dev.groupx.apkikala.ui.screen.post.ImageCommon


@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    userId: String,
    viewModel: PersonalProfileViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.getDetails(userId)
    }

//    Column(
//        modifier = modifier
//            .background(color = MaterialTheme.colorScheme.tertiary)
//            .fillMaxWidth()
//            .padding(vertical = 5.dp)
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//    }

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ), shape = RoundedCornerShape(4.dp), elevation = CardDefaults.cardElevation(
            4.dp
        ), modifier = modifier.padding(10.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp), contentAlignment = Alignment.Center) {
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


@Composable
fun Stats(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            4.dp
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(5.dp)
                .width(80.dp)
        ) {
            Text(
                text = numberText,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 15.sp
            )

        }
    }

}

@Composable
fun ProfileDescription(
    displayName: String,
    bio: String,
    address: String,
    modifier: Modifier = Modifier,
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Column(Modifier.padding(top = 10.dp)) {
            Text(
                text = bio,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
            Text(
                text = address,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
