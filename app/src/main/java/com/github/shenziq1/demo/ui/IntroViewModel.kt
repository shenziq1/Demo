package com.github.shenziq1.demo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.github.shenziq1.demo.R
import com.github.shenziq1.demo.data.GameId
import com.github.shenziq1.demo.data.GameInfoData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IntroViewModel: ViewModel() {
    private val TAG = "introViewModel"
    private var gameInfoData = GameInfoData.data[GameId(0)]
    private val _uiState = MutableStateFlow(IntroUiState())
    val uiState: StateFlow<IntroUiState> = _uiState.asStateFlow()

    init {
        reset()
    }


    fun updateLike() {
        if (uiState.value.liked){
            _uiState.update {it.copy(liked = false, likeRes = R.drawable.like, likedCount = it.likedCount-1 )}
            Log.d(TAG, "like is canceled ${gameInfoData!!.likeCount} ${_uiState.value.likedCount}")
        }

        else{
            _uiState.update {it.copy(liked = true, likeRes = R.drawable.liked, likedCount = it.likedCount+1 )}
            Log.d(TAG, "like the content ${gameInfoData!!.likeCount} ${_uiState.value.likedCount}")
        }

    }

    fun updateStar() {
        if (uiState.value.stared){
            _uiState.update {current -> current.copy(stared = false, starRes = R.drawable.star)}
            Log.d(TAG, "star is canceled")
        }

        else{
            _uiState.update {current -> current.copy(stared = true, starRes = R.drawable.stared)}
            Log.d(TAG, "star the content")
        }

    }

    private fun reset(){
        _uiState.update { it.copy(gamePlayed = gameInfoData!!.gamePlayed, likedCount = gameInfoData!!.likeCount) }
    }

}