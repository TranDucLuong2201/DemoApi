package com.example.unsplash.demo_api

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unsplash.databinding.ActivityDemoApiBinding


class DemoApiActivity : AppCompatActivity() {
    private val viewModel by viewModels<DemoApiViewModel>(
        factoryProducer = {
            viewModelFactory {
                addInitializer(DemoApiViewModel::class) {
                    DemoApiViewModel(ServiceLocator.todoService)
                }
            } }
    )

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDemoApiBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        val policy = ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)

        binding.button.setOnClickListener {
            viewModel.postUsersSuspend()
        }


        viewModel.toolLiveData.observe(this){ result ->
            when(result){
                is TodoUiState.Error -> {
                    binding.textView.text = "Errol: ${result.throwable.message}"
                }
                TodoUiState.Loading -> {
                    binding.textView.text = "Loading"
                }
                is TodoUiState.Success -> {
                    binding.textView.text = result.todo.title
                }
                is TodoUiState.SuccessList -> {
                    binding.textView.text = result.items.joinToString { it.name }
                }
                is TodoUiState.SuccessPost -> {
                    binding.textView.text = result.post.toString()
                }
            }
        }
    }
}