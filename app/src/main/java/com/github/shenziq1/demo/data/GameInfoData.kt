package com.github.shenziq1.demo.data

object GameInfoData {
    var data = mutableMapOf<GameId, GameInfo>(
        Pair(
            GameId(id = 0), GameInfo(
                name = "Player Unknown's Unknown Game",
                author = "Unknown",
                gamePlayed = 100,
                likeCount = 10,
                introText = "This is a test intro just for testing purpose. I don't know what to say, have fun all!",
                category = "RPG",
                coin = 888,
                diamond = 666
            )
        ),
        Pair(
            GameId(id = 1), GameInfo(
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
    )
}