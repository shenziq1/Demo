package com.github.shenziq1.demo.ui

import androidx.annotation.DrawableRes
import com.github.shenziq1.demo.R

data class IntroUiState(
    val liked: Boolean = false,
    val stared: Boolean = false,
    @DrawableRes val likeRes: Int = R.drawable.like,
    @DrawableRes val starRes: Int = R.drawable.star,
    val played: Boolean = false,
    val likedCount: Int = 0,
    val gamePlayed: Int = 0,

    )