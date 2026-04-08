package com.battlebucks.leaderboard.presentation.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battlebucks.leaderboard.domain.usecase.ObserveLeaderboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val observeLeaderboardUseCase: ObserveLeaderboardUseCase
) : ViewModel() {

    private val _uiState  = MutableStateFlow(LeaderboardUiState())
    val uiState : StateFlow<LeaderboardUiState> = _uiState.asStateFlow()

    init {
        observeLeaderboard()
    }

    fun observeLeaderboard() {
        viewModelScope.launch {
            observeLeaderboardUseCase()
                .onStart {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }.catch { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message
                        )
                    }
                }.collect { players ->
                        _uiState.update {
                            it.copy(
                                players = players,
                                isLoading = false,
                                error = null
                            )
                        }
                }
        }
    }
}