package com.example.unsplash.demo_api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DemoApiViewModel(private val todoService: TodoService) : ViewModel() {
    private val _mutableLiveData = MutableLiveData<TodoUiState>()
    val toolLiveData: LiveData<TodoUiState> get() = _mutableLiveData

    fun getTodo() {
        val result: Response<TodoResponse> = todoService.getTodos().execute()
        if (result.isSuccessful) {
            _mutableLiveData.value = TodoUiState.Success(result.body()!!)
        } else {
            _mutableLiveData.value = TodoUiState.Error(Throwable(result.message()))

        }
    }

    fun getTodoEnqueue() {
        _mutableLiveData.value = TodoUiState.Loading

        todoService.getTodos().enqueue(object : Callback<TodoResponse> {
            override fun onResponse(response: Call<TodoResponse>, p1: Response<TodoResponse>) {
                if (p1.isSuccessful) {
                    _mutableLiveData.value = TodoUiState.Success(p1.body()!!)
                }
            }

            override fun onFailure(p0: Call<TodoResponse>, throwable: Throwable) {
                _mutableLiveData.value = TodoUiState.Error(throwable)
            }
        })
    }

    fun getTodoSuspend() {
        _mutableLiveData.value = TodoUiState.Loading
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    todoService.getTodosSuspend()
                }
                _mutableLiveData.value = TodoUiState.Success(result)
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (throwable: Throwable) {
                _mutableLiveData.value = TodoUiState.Error(throwable)
            }
        }

    }
}