package com.battlebucks.leaderboard.data.engine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class ScoreGenerator {

    private val random = Random(System.currentTimeMillis())

    private val players = listOf(
        "Anil", "Rahul", "Rohit", "Aman", "Vikas",
        "Deepak", "Karan", "Amit", "Nikhil", "Sumit",
        "Ajay", "Rakesh", "Mohit", "Pankaj", "Vivek"
    )

    fun generateScores(): Flow<Pair<Int, Int>> = flow {

        val scores = MutableList(players.size) { 0 }

        while (true) {

            delay(random.nextLong(500, 2000))

            val playerIndex = random.nextInt(players.size)

            val scoreIncrease = random.nextInt(5, 50)

            scores[playerIndex] += scoreIncrease

            emit(playerIndex to scores[playerIndex])
        }
    }

    fun getPlayers(): List<String> = players
}