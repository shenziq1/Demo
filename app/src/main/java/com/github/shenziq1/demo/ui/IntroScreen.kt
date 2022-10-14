package com.github.shenziq1.demo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.shenziq1.demo.R
import com.github.shenziq1.demo.data.GameInfoData
import com.github.shenziq1.demo.ui.theme.DemoTheme

// from here: https://stackoverflow.com/questions/67982230/jetpack-compose-pass-parameter-to-viewmodel
// Just use this for simple illustration
class MyViewModelFactory(private val gameId: Int) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = IntroViewModel(gameId) as T
}
//

@Composable
fun IntroScreen(
    gameId: Int,
    modifier: Modifier = Modifier,
    viewModel: IntroViewModel = viewModel(factory = MyViewModelFactory(gameId)),
    gameInfoData: GameInfoData = GameInfoData
) {
    val introUiState by viewModel.uiState.collectAsState()
    val introUiStaticProperty = gameInfoData.data[gameId]

    var expansionButtonClicked by remember { mutableStateOf(false) }

    Scaffold(modifier = modifier) {
        //background
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.kiana),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            NavigationBar(
                introUiState.likeRes,
                introUiState.starRes,
                { viewModel.updateLike() },
                { viewModel.updateStar() }
            )
            Card(
                backgroundColor = (MaterialTheme.colors.surface.copy(alpha = 0f)),
                modifier = Modifier.clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface.copy(alpha = 0.5f))
                        .padding(20.dp, 20.dp, 20.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleBar(
                        name = introUiStaticProperty.name,
                        author = introUiStaticProperty.author,
                        gamePlayed = introUiState.gamePlayed,
                        likedCount = introUiState.likedCount,
                        playButtonOnClick = { viewModel.updateGamePlayed() }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CategoryBar(
                        category = introUiStaticProperty.category,
                        coin = introUiStaticProperty.coin,
                        diamond = introUiStaticProperty.diamond
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = introUiStaticProperty.introText)
                    IconButton(onClick = { expansionButtonClicked = !expansionButtonClicked }) {
                        Icon(
                            imageVector = if (expansionButtonClicked) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = "Expand more"
                        )
                    }
                }
            }
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
            .padding(20.dp)
            .background(color = MaterialTheme.colors.background.copy(alpha = 0.0f)),
        horizontalArrangement = Arrangement.SpaceBetween
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
fun TitleBar(
    name: String,
    author: String,
    gamePlayed: Int,
    likedCount: Int,
    playButtonOnClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        TitleInfo(name = name, gamePlayed = gamePlayed, likedCount = likedCount)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "@$author",
            color = MaterialTheme.colors.onSecondary,
            fontStyle = FontStyle(1)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End).clip(RoundedCornerShape(20.dp)),
            onClick = playButtonOnClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant),
        ) {
            Text(text = "Play", color = MaterialTheme.colors.onSurface)
        }
    }
}

@Composable()
fun TitleInfo(name: String, gamePlayed: Int, likedCount: Int) {
    Column(modifier = Modifier.fillMaxWidth(0.4F)) {
        Text(text = name, fontSize = 18.sp, color = MaterialTheme.colors.onSurface)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconWithValue(
                space = 0.5F,
                iconId = R.drawable.games,
                iconDescription = "game played",
                value = gamePlayed.toString(),
                size = 20,
                color = MaterialTheme.colors.onSecondary
            )
            IconWithValue(
                space = 1F,
                iconId = R.drawable.like_small,
                iconDescription = "like counts",
                value = likedCount.toString(),
                size = 20,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
fun IconWithValue(
    space: Float,
    iconId: Int,
    iconDescription: String,
    value: String,
    size: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
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
        Text(text = value, color = color)
    }
}

@Composable
fun CategoryBar(category: String, coin: Int, diamond: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconWithValue(
            space = 0.5F,
            iconId = R.drawable.category,
            iconDescription = "category",
            value = category,
            size = 30
        )

        IconWithValue(
            space = 0.5F,
            iconId = R.drawable.coin,
            iconDescription = "coin",
            value = coin.toString(),
            size = 30,
            horizontalArrangement = Arrangement.End

        )
        IconWithValue(
            space = 1F,
            iconId = R.drawable.diamond,
            iconDescription = "diamond",
            value = diamond.toString(),
            size = 30,
            horizontalArrangement = Arrangement.End
        )

    }
}

@Composable
fun ExpansionButton(onExpansionButtonClicked: () -> Unit) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoTheme {
        IntroScreen(0)
    }
}