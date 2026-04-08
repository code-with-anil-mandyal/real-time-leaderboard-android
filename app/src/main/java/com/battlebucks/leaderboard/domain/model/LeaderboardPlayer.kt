package com.battlebucks.leaderboard.domain.model

data class LeaderboardPlayer(
    val id: Int,
    val rank: Int,
    val name: String,
    val score: Int
)
