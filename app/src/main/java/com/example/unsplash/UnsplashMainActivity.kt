package com.example.unsplash

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import com.example.unsplash.databinding.ActivityUnsplashMainBinding
import com.example.unsplash.presentation.feed.FeedsFragment

class UnsplashMainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityUnsplashMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FeedsFragment>(
                    containerViewId = R.id.container_view,
                    tag = FeedsFragment::class.java.simpleName
                )
            }
        }
    }
}