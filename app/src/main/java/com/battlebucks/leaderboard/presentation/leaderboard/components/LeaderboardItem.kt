package com.battlebucks.leaderboard.presentation.leaderboard.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.battlebucks.leaderboard.R
import com.battlebucks.leaderboard.domain.model.LeaderboardPlayer
import com.battlebucks.leaderboard.presentation.common.getAvatar
import com.battlebucks.leaderboard.presentation.ui.theme.CurrentUserCard

@Composable
fun LeaderboardItem(player: LeaderboardPlayer, modifier: Modifier = Modifier) {

    val isCurrentUser = player.name == stringResource(R.string.dummy_current_user) //Dummy current user

    val textColor =
        if (isCurrentUser) Color.White
        else MaterialTheme.colorScheme.onSurface


    val highlightColor by animateColorAsState(
        targetValue =
            if (player.name == stringResource(R.string.dummy_current_user))
                CurrentUserCard
            else
                MaterialTheme.colorScheme.surface,
        animationSpec = tween(500),
        label = ""
    )

    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        ),
        label = ""
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        colors = CardDefaults.cardColors(
            containerColor = highlightColor
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 18.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = "#${player.rank}",
                modifier = Modifier.width(40.dp),
                style = MaterialTheme.typography.titleMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = getAvatar(player.id),
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = player.name,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                color = textColor
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(stringResource(R.string.trophy))

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = player.score.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor
                )
            }

        }

    }
}