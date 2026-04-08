package com.battlebucks.leaderboard.presentation.common

fun getAvatar(index: Int): String {

    val avatars = listOf(
        "🧑‍🚀","👨‍💻","🧙‍♂️","🦸‍♂️",
        "🥷","👾","🤖","🧛‍♂️",
        "🦊","🐯","🐼","🐵"
    )

    return avatars[index % avatars.size]
}