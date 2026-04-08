package com.battlebucks.leaderboard.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(

    primary = RedPrimary,
    onPrimary = Color.White,

    secondary = RedAccent,
    onSecondary = Color.Black,

    background = BackgroundDark,
    onBackground = TextPrimaryDark,

    surface = SurfaceDark,
    onSurface = TextPrimaryDark,

    surfaceVariant = SurfaceVariantDark,

    outline = DividerDark

)





@Composable
fun BattleBucksLeaderboardAssignmentTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}