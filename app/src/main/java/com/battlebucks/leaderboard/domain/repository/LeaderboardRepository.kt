package com.battlebucks.leaderboard.domain.repository

import com.battlebucks.leaderboard.domain.model.LeaderboardPlayer
import kotlinx.coroutines.flow.Flow

interface LeaderboardRepository {
    fun observeLeaderboard(): Flow<List<LeaderboardPlayer>>
}