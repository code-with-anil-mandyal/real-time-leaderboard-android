package com.battlebucks.leaderboard.domain.usecase

import com.battlebucks.leaderboard.domain.model.LeaderboardPlayer
import com.battlebucks.leaderboard.domain.repository.LeaderboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveLeaderboardUseCase @Inject constructor(
    private val repository: LeaderboardRepository
) {

    operator fun invoke(): Flow<List<LeaderboardPlayer>> {
        return repository.observeLeaderboard()
    }
}