package com.github.shenziq1.demo.ui

import com.github.shenziq1.demo.R

data class IntroUiState(
    val liked: Boolean = false,
    val stared: Boolean = false,
    val likeRes: Int = R.drawable.like,
    val starRes: Int = R.drawable.star,
    val played: Boolean = false,
    val likedCount: Int = 0,
    val playedCount: Int = 0
)