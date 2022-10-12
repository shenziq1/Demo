package com.github.shenziq1.demo.data

object GameInfoData {
    var data = mutableListOf<GameInfo>(
        GameInfo(
            gameId = 0,
            name = "Player Unknown's Unknown Game",
            author = "Unknown",
            gamePlayed = 100,
            likeCount = 10,
            introText = "This is a test intro just for testing purpose. I don't know what to say, have fun all!",
            category = "RPG",
            coin = 888,
            diamond = 666
        ),
        GameInfo(
            gameId = 1,
            name = "Another Game",
            author = "Unknown",
            gamePlayed = 1000,
            likeCount = 100,
            introText = "This is a test intro just for testing purpose. I don't know what to say, have fun all!",
            category = "RPG",
            coin = 888,
            diamond = 666
        )
    )
}