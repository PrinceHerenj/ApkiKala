package dev.groupx.apkikala.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.groupx.apkikala.model.SearchResult
import dev.groupx.apkikala.ui.screen.post.GetProfileImage
import dev.groupx.apkikala.ui.screen.post.ImageCommon

@Composable
fun SearchItem(
    searchResult: SearchResult,
    openAndPopUp: (String, String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
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
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .clickable { viewModel.onSearchItemClick(openAndPopUp, searchResult.user)}
    ) {
        SearchResultItem(
            profileImageUrl = searchResult.profileImageUrl,
            username = searchResult.username
        )
    }
}

@Composable
fun SearchResultItem(profileImageUrl: String, username: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .padding(horizontal = 8.dp),
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
    }
}