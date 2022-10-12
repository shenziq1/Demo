package com.github.shenziq1.demo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.shenziq1.demo.R
import com.github.shenziq1.demo.data.GameId
import com.github.shenziq1.demo.data.GameInfoData
import com.github.shenziq1.demo.ui.theme.DemoTheme

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    viewModel: IntroViewModel = viewModel(),
    gameInfoData: GameInfoData = GameInfoData()
) {
    val introUiState by viewModel.uiState.collectAsState()
    val introUiStaticProperty = gameInfoData.data[GameId(0)]

    if (introUiStaticProperty != null) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            NavigationBar(
                introUiState.likeRes,
                introUiState.starRes,
                { viewModel.updateLike() },
                { viewModel.updateStar() }
            )
            TitleBar(
                name = introUiStaticProperty.name,
                author = introUiStaticProperty.author,
                gamePlayed = introUiState.gamePlayed,
                likedCount = introUiState.likedCount
            )
        }
    }
}

@Composable
fun NavigationBar(
    likeId: Int,
    starId: Int,
    onLikeChanged: () -> Unit,
    onStarChanged: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "back",
            modifier = Modifier.size(30.dp)
        )
        Row {
            Image(
                painter = painterResource(id = likeId),
                contentDescription = "like",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = onLikeChanged)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(id = starId),
                contentDescription = "star",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = onStarChanged)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(id = R.drawable.share),
                contentDescription = "share",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable()
fun TitleBar(name: String, author: String, gamePlayed: Int, likedCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray),
        verticalAlignment = Alignment.Top
    ) {
        TitleInfo(name = name, gamePlayed = gamePlayed, likedCount = likedCount)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "@$author")
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Play")
        }
    }
}

@Composable()
fun TitleInfo(name: String, gamePlayed: Int, likedCount: Int) {
    Column(modifier = Modifier.fillMaxWidth(0.4F)) {
        Text(text = name, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconWithValue(
                space = 0.5F,
                iconId = R.drawable.games,
                iconDescription = "game played",
                valueId = gamePlayed,
                size = 20
            )
            IconWithValue(
                space = 1F,
                iconId = R.drawable.like_small,
                iconDescription = "like counts",
                valueId = likedCount,
                size = 20
            )
        }
    }
}

@Composable
fun IconWithValue(
    space: Float,
    iconId: Int,
    iconDescription: String,
    valueId: Int,
    size: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        modifier = modifier.fillMaxWidth(space),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        Image(
            modifier = Modifier.size(size.dp),
            painter = painterResource(id = iconId),
            contentDescription = iconDescription
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = valueId.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoTheme {
        IntroScreen()
    }
}