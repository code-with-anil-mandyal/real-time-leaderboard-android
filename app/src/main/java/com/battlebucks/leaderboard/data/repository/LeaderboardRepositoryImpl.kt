package com.battlebucks.leaderboard.data.repository


import com.battlebucks.leaderboard.data.engine.ScoreGenerator
import com.battlebucks.leaderboard.domain.model.LeaderboardPlayer
import com.battlebucks.leaderboard.domain.repository.LeaderboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

class LeaderboardRepositoryImpl @Inject constructor(
    private val scoreGenerator: ScoreGenerator
) : LeaderboardRepository {

    override fun observeLeaderboard(): Flow<List<LeaderboardPlayer>> {

        val players = scoreGenerator.getPlayers()

        return scoreGenerator.generateScores()
            .scan(createInitialLeaderboard(players)) { leaderboard, update ->

                val (playerIndex, newScore) = update

                val updatedList = leaderboard.toMutableList()

                val player = updatedList[playerIndex]

                updatedList[playerIndex] =
                    player.copy(score = newScore)

                calculateRanks(updatedList)
            }
    }

    private fun createInitialLeaderboard(
        players: List<String>
    ): List<LeaderboardPlayer> {

        return players.mapIndexed { index, name ->
            LeaderboardPlayer(
                id = index,
                name = name,
                score = 0,
                rank = index + 1
            )
        }
    }

   internal fun calculateRanks(
        players: List<LeaderboardPlayer>
    ): List<LeaderboardPlayer> {

        val sorted = players
            .sortedByDescending { it.score }

        var currentRank = 1

        return sorted.mapIndexed { index, player ->

            if (index > 0 &&
                player.score < sorted[index - 1].score
            ) {
                currentRank = index + 1
            }

            player.copy(rank = currentRank)
        }
    }
}