package com.example.myapplication.presentation.stateMachine

import com.example.domain.model.StandardError

sealed class FullPageState {
    object Loading : FullPageState()
    object Success : FullPageState()
    class Error(val error: StandardError) : FullPageState()
}
