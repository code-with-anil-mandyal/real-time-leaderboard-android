package com.battlebucks.leaderboard.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.battlebucks.leaderboard.presentation.ui.theme.RankBadgeRed

@Composable
fun RankBadge(rank: Int) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = RankBadgeRed
        ),
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(
            modifier = Modifier
                .height(36.dp)
                .widthIn(min = 70.dp),
            contentAlignment = Alignment.Center
        ) {


            AnimatedContent(
                targetState = rank,
                transitionSpec = {
                    slideInVertically { it } + fadeIn() togetherWith
                            slideOutVertically { -it } + fadeOut()
                },
                label = ""
            ) { targetRank ->

                Text(
                    text = "${targetRank}th",
                    color = Color.White
                )
            }
        }

    }
}