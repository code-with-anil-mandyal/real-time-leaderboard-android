package com.battlebucks.leaderboard.presentation.leaderboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.battlebucks.leaderboard.R
import com.battlebucks.leaderboard.domain.model.LeaderboardPlayer
import com.battlebucks.leaderboard.presentation.components.RankBadge
import com.battlebucks.leaderboard.presentation.components.TrophyBadge
import com.battlebucks.leaderboard.presentation.ui.theme.FireOrange
import com.battlebucks.leaderboard.presentation.ui.theme.FireRed
import com.battlebucks.leaderboard.presentation.ui.theme.FireYellow
import com.battlebucks.leaderboard.presentation.ui.theme.LeaderboardGradientBottom
import com.battlebucks.leaderboard.presentation.ui.theme.LeaderboardGradientTop

@Composable
fun LeaderboardHeader(
    height: Dp,
    players: List<LeaderboardPlayer>,
    modifier: Modifier = Modifier
) {

    val maxHeight = 240.dp
    val minHeight = 90.dp

    val collapseProgress =
        ((maxHeight - height) / (maxHeight - minHeight))
            .coerceIn(0f, 1f)

    val iconSize =
        lerp(90.dp, 24.dp, collapseProgress)

    val textSize =
        lerp(26.sp, 16.sp, collapseProgress)

    val spacing =
        lerp(16.dp, 8.dp, collapseProgress)

    val isCollapsed = collapseProgress > 0.7f

    val currentUser =
        players.find { it.name == stringResource(R.string.dummy_current_user) }



    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                Brush.verticalGradient(
                    listOf(
                        LeaderboardGradientTop,
                        LeaderboardGradientBottom
                    )
                )
            )
            .padding(horizontal = 16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = if (isCollapsed) 8.dp else 0.dp,
                    bottom = if (isCollapsed) 8.dp else 0.dp
                ),
            horizontalAlignment =
                if (isCollapsed)
                    Alignment.Start
                else
                    Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Collapsed Layout
            if (isCollapsed) {

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = stringResource(R.string.fire),
                                fontSize = 25.sp
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            RankBadge(
                                rank = currentUser?.rank ?: 0
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            TrophyBadge(
                                score = currentUser?.score ?: 0
                            )
                        }
                    }




                }


            }

            // Expanded Layout
            else {

                Text(
                    text = stringResource(R.string.fire),
                    fontSize = iconSize.value.sp
                )

                Spacer(modifier = Modifier.height(spacing))

                Text(
                    text = stringResource(R.string.legends),
                    fontSize = textSize,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                FireYellow,
                                FireOrange,
                                FireRed
                            )
                        )
                    ),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {

                    RankBadge(
                        rank = currentUser?.rank ?: 0
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TrophyBadge(
                        score = currentUser?.score ?: 0
                    )

                }


            }

        }

    }
}