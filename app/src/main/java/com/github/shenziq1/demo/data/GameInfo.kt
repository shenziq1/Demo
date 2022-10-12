package com.github.shenziq1.demo.data

data class GameInfo(
    val gameId: Int = 0,
    val name: String = "unknown",
    val author: String = "unknown",
    val gamePlayed: Int = 0,
    val likeCount: Int = 0,
    val introText: String = "to be continued",
    val category: String = "NUL",
    val coin: Int = 0,
    val diamond: Int = 0
)
