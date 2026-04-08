package com.battlebucks.leaderboard.presentation.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.battlebucks.leaderboard.R
import com.battlebucks.leaderboard.presentation.leaderboard.components.LeaderboardHeader
import com.battlebucks.leaderboard.presentation.leaderboard.components.LeaderboardItem

@Composable
fun LeaderboardScreen(
    viewModel: LeaderboardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    val maxHeaderHeight = 240.dp
    val minHeaderHeight = 72.dp

    val density = LocalDensity.current

    val maxHeightPx = with(density) { maxHeaderHeight.toPx() }

    var headerOffset by remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {

        object : NestedScrollConnection {

            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {

                val delta = available.y

                val isListAtTop =
                    listState.firstVisibleItemIndex == 0 &&
                            listState.firstVisibleItemScrollOffset == 0

                if (isListAtTop || delta < 0) {

                    headerOffset =
                        (headerOffset + delta)
                            .coerceIn(-maxHeightPx, 0f)
                }

                return Offset.Zero
            }
        }
    }

    val headerHeight =
        (maxHeightPx + headerOffset)
            .coerceAtLeast(
                with(density) { minHeaderHeight.toPx() }
            )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0B0F))
            .nestedScroll(nestedScrollConnection)
    ) {

        LeaderboardHeader(
            height = with(density) { headerHeight.toDp() },
            players = uiState.players,
            modifier = Modifier.zIndex(1f)
        )



        LazyColumn(

            state = listState,

            modifier = Modifier
                .fillMaxSize()
                .offset {
                    IntOffset(
                        x = 0,
                        y = headerHeight.toInt()
                    )
                },
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 80.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {



            stickyHeader {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.leaderboard),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Start
                    )
                }

            }

            items(
                uiState.players,
                key = {it.id}
            ) { player ->

                LeaderboardItem(
                    player,
                    modifier = Modifier.animateItem()
                )

            }
        }
    }
}