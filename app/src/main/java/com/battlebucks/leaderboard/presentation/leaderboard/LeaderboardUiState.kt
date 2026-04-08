package com.battlebucks.leaderboard.presentation.leaderboard

import com.battlebucks.leaderboard.domain.model.LeaderboardPlayer

data class LeaderboardUiState(
    val players: List<LeaderboardPlayer> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)