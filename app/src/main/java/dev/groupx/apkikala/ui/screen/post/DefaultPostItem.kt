package dev.groupx.apkikala.ui.screen.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.groupx.apkikala.R


@Composable
fun DefaultPostItem() {
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
            DefaultPostTopBar()
            DefaultPostContent()
            DefaultBottomAppBar()
        }
    }
    DefaultPostDescription()
}

@Composable
private fun DefaultPostDescription() {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(text = "")
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "")
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 10.dp)
        )

    }
}

@Composable
private fun DefaultBottomAppBar() {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.primary,
    modifier = Modifier
        .padding(bottom = 8.dp)
        .height(48.dp)
    ) {
        IconButton(onClick = { }) {
            Icon(Icons.Filled.FavoriteBorder, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Notifications, contentDescription = null)
        }
    }
}

@Composable
private fun DefaultPostContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.post_image_blank),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(400.dp)
                .height(400.dp)
        )
    }
}

@Composable
private fun DefaultPostTopBar() {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .width(48.dp)
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.blank_image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .width(48.dp)
                    .height(48.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { }) {
            Icon(Icons.Filled.MoreVert, contentDescription = null)
        }
    }
}
