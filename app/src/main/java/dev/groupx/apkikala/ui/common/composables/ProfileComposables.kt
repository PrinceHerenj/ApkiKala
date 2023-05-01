package dev.groupx.apkikala.ui.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Stats(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            8.dp
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
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Color.Black,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Column(Modifier.padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
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
        Spacer(modifier = Modifier.height(12.dp))
    }
}

