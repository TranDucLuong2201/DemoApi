package com.example.unsplash.demo_api

interface TodoUiState {
    data object Loading: TodoUiState
    data class Success(val todo: TodoResponse): TodoUiState
    data class Error(val throwable: Throwable): TodoUiState
}