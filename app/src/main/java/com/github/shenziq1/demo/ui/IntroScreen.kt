package com.github.shenziq1.demo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.shenziq1.demo.R
import com.github.shenziq1.demo.ui.theme.DemoTheme

@Composable
fun IntroScreen(modifier: Modifier = Modifier, introViewModel: IntroViewModel = IntroViewModel()) {
    val introUiState by introViewModel.uiState.collectAsState()

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        NavigationBar(
            introUiState.likeRes,
            introUiState.starRes,
            { introViewModel.updateLike() },
            { introViewModel.updateStar() })
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

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoTheme {
        IntroScreen()
    }
}