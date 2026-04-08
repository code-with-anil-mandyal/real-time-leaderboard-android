package com.battlebucks.leaderboard.presentation.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.battlebucks.leaderboard.presentation.ui.theme.TrophyBadgeBackground

@Composable
fun TrophyBadge(score: Int) {

    val animatedScore by animateIntAsState(
        targetValue = score,
        animationSpec = tween(600),
        label = ""
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = TrophyBadgeBackground
        ),
        shape = RoundedCornerShape(20.dp)
    ) {



        Row(
            modifier = Modifier
                .height(36.dp)
                .widthIn(min = 70.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "🏆",
                fontSize = 14.sp
            )

            Spacer(Modifier.width(4.dp))

            Text(
                animatedScore.toString(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}