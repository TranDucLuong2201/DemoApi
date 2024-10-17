package com.example.unsplash.okhttp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun buildGson(): Gson = TODO()
fun buildMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

class DemoMoshiGsonViewModel(application: Application) : AndroidViewModel(application) {
    private val moshi by lazy { buildMoshi() }
    private val gson by lazy { buildGson() }

    private val todoApiService by lazy {
        createTodoApiService()
    }

    private val _stateLiveData = MutableLiveData<String?>(null)
    val stateLiveData: LiveData<String?> get() = _stateLiveData

    fun parse() {
        viewModelScope.launch {
            _stateLiveData.value = "Loading"

            try {
                _stateLiveData.value = todoApiService.getTodos().toString()
                Log.d(TAG, "Parse json successfully")
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (e: Exception) {
                Log.e(TAG, "Failed to parse json", e)
                _stateLiveData.value = e.message.orEmpty()
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private suspend fun parseJsonInternal(): String =
        withContext(Dispatchers.IO) {
            val jsonString = getApplication<Application>()
                .readAssetFile("student.json")

            val adapter = moshi.adapter(Student::class.java)
            val student = adapter.fromJson(jsonString)
            student?.toString() ?: "<null>"
        }
    private companion object {
        private val TAG = DemoMoshiGsonViewModel::class.java.simpleName
    }

}