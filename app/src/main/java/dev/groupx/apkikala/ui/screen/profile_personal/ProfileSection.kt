package dev.groupx.apkikala.ui.screen.profile_personal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.R


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


    Box(modifier = modifier) {
        if (uiState.loading) CircularProgressIndicator()
        else {
            Column(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icons8_son_goku),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = modifier
                            .size(100.dp)
                            .border(
                                width = 1.dp, color = Color.LightGray, shape = CircleShape
                            )
                            .padding(2.dp)
                            .clip(CircleShape)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(top = 250.dp)
            ) {
                Card(elevation = CardDefaults.cardElevation(3.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = modifier.padding(horizontal = 45.dp)


                    ) {
                        Stats(numberText = uiState.profile.posts.toString(), text = "Posts")
                        Spacer(modifier = Modifier.weight(4f))
                        Stats(numberText = uiState.profile.followers.toString(), text = "Followers")
                        Spacer(modifier = Modifier.weight(3f))
                        Stats(numberText = uiState.profile.following.toString(), text = "Following")
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    ProfileDescription(
                        displayName = uiState.profile.username,
                        description = "${uiState.profile.bio}  ${uiState.profile.address}",
                        url = "Rakess@gmail.com",
                        followedBy = listOf("bablu", "parkaass"),
                        otherFollow = (uiState.profile.followers.toInt() - 2)
                    )
                }
            }

        }
    }


}


@Composable
fun Stats(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 15.sp
        )

    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherFollow: Int,
    modifier: Modifier = Modifier,
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        Text(
            text = url, color = Color.Blue, letterSpacing = letterSpacing, lineHeight = lineHeight
        )
        if (followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = Color.Black, fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    pushStyle(boldStyle)
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }

                    }
                    if (otherFollow > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherFollow others")
                    }
                }, letterSpacing = letterSpacing, lineHeight = lineHeight
            )
        }
    }
}

