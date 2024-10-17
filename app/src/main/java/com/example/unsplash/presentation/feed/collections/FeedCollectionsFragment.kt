package com.example.unsplash.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.unsplash.core.base.BaseFragment
import com.example.unsplash.databinding.FragmentFeedCollectionsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FeedCollectionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedCollectionsFragment : BaseFragment<FragmentFeedCollectionsBinding>(
    inflate = FragmentFeedCollectionsBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: setup views
    }

    companion object {
        fun newInstance() = FeedCollectionsFragment()
    }
}