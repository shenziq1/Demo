package com.github.shenziq1.demo.data

data class GameInfo(
    val name: String = "unknown",
    val author: String = "unknown",
    val timePlayed: Int = 0,
    val liked: Int = 0,
    val introText: String = "to be continued",
    val category: String = "NUL",
    val coin: Int = 0,
    val diamond: Int = 0
)
