package com.github.shenziq1.demo.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.shenziq1.demo.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IntroViewModel: ViewModel() {
    private val TAG = "introViewModel"
    private val _uiState = MutableStateFlow(IntroUiState())
    val uiState: StateFlow<IntroUiState> = _uiState.asStateFlow()

    init {
        reset()
    }


    fun updateLike() {
        if (uiState.value.liked){
            _uiState.update {current -> current.copy(liked = false, likeRes = R.drawable.like)}
            Log.d(TAG, "like is canceled")
        }

        else{
            _uiState.update {current -> current.copy(liked = true, likeRes = R.drawable.liked)}
            Log.d(TAG, "like the content")
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
        _uiState.value = IntroUiState()
    }
}