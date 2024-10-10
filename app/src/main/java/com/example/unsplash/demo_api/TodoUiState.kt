package com.example.unsplash.demo_api

interface TodoUiState {
    data object Loading: TodoUiState
    data class Success(val todo: TodoResponse): TodoUiState
    data class SuccessList(val items: List<GetListDemoItem>): TodoUiState
    data class Error(val throwable: Throwable): TodoUiState
    data class SuccessPost(val post: PostDemoResponse): TodoUiState
}