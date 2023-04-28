package dev.groupx.apkikala.ui.screen.comment_screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.groupx.apkikala.model.Comment

@Composable
fun CommentItem(comment: Comment) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        CommentComposable(commentContent = comment.commentContent, username = comment.userId)
    }
}

@Composable
fun CommentComposable(commentContent: String, username: String) {
    Row() {
        Text(text = username, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = commentContent, fontWeight = FontWeight.Normal)
    }
}
