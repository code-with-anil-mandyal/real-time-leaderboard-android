package com.battlebucks.leaderboard

import com.battlebucks.leaderboard.data.engine.ScoreGenerator
import com.battlebucks.leaderboard.data.repository.LeaderboardRepositoryImpl
import com.battlebucks.leaderboard.domain.model.LeaderboardPlayer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Before


@OptIn(ExperimentalCoroutinesApi::class)
class LeaderboardRepositoryTest {

    private lateinit var scoreGenerator: ScoreGenerator
    private lateinit var repository: LeaderboardRepositoryImpl

    @Before
    fun setup() {
        scoreGenerator = ScoreGenerator()
        repository = LeaderboardRepositoryImpl(scoreGenerator)
    }

    // Initial leaderboard
    @Test
    fun `initial leaderboard should have zero scores`() = runTest {

        val leaderboard = repository.observeLeaderboard().first()

        assertTrue(leaderboard.all { it.score == 0 })
    }

    // Ranking order
    @Test
    fun `leaderboard should be sorted by score descending`() = runTest {

        val leaderboard = repository.observeLeaderboard()
            .take(5)
            .last()

        val sorted = leaderboard.sortedByDescending { it.score }

        assertEquals(sorted, leaderboard)
    }

    // Same score same rank
    @Test
    fun `players with same score should have same rank`() {

        val players = listOf(
            LeaderboardPlayer(1, 1, "A", 100),
            LeaderboardPlayer(2, 2, "B", 100),
            LeaderboardPlayer(3, 3, "C", 90),
        )

        val result = repository.calculateRanks(players)

        assertEquals(1, result[0].rank)
        assertEquals(1, result[1].rank)
        assertEquals(3, result[2].rank)
    }

    // Rank skipping test
    @Test
    fun `rank should skip correctly`() {

        val players = listOf(
            LeaderboardPlayer(1, 1, "A", 120),
            LeaderboardPlayer(2, 2, "B", 100),
            LeaderboardPlayer(3, 3, "C", 100),
            LeaderboardPlayer(4, 4, "D", 90),
        )

        val result = repository.calculateRanks(players)

        assertEquals(1, result[0].rank)
        assertEquals(2, result[1].rank)
        assertEquals(2, result[2].rank)
        assertEquals(4, result[3].rank)
    }
}