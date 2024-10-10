package com.example.unsplash.okhttp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.unsplash.databinding.ActivityDemoMoshiGsonBinding

class DemoMoshiGsonActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDemoMoshiGsonBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<DemoMoshiGsonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.parse()
        }
        viewModel.stateLiveData.observe(this) {
            binding.textView.text = it
        }
    }
}