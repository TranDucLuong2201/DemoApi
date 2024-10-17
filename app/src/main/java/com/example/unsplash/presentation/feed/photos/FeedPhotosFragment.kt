package com.example.unsplash.presentation.feed.photos

import android.os.Bundle
import com.example.unsplash.core.base.BaseFragment
import com.example.unsplash.databinding.FragmentFeedPhotosBinding

class FeedPhotosFragment: BaseFragment<FragmentFeedPhotosBinding>(
    inflate = FragmentFeedPhotosBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
         fun newInstance() = FeedPhotosFragment()
    }
}