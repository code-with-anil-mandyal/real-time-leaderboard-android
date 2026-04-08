package com.battlebucks.leaderboard.di

import com.battlebucks.leaderboard.data.engine.ScoreGenerator
import com.battlebucks.leaderboard.data.repository.LeaderboardRepositoryImpl
import com.battlebucks.leaderboard.domain.repository.LeaderboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideScoreGenerator(): ScoreGenerator{
        return ScoreGenerator()
    }

    @Provides
    @Singleton
    fun provideLeaderboardRepository(scoreGenerator: ScoreGenerator): LeaderboardRepository {
        return LeaderboardRepositoryImpl(scoreGenerator)
    }
}