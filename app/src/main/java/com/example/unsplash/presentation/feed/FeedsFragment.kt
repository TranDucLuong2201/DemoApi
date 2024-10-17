package com.example.unsplash.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.unsplash.R
import com.example.unsplash.core.base.BaseFragment
import com.example.unsplash.databinding.FragmentFeedsBinding
import com.example.unsplash.presentation.feed.collections.FeedCollectionsFragment
import com.example.unsplash.presentation.feed.photos.FeedPhotosFragment
import com.example.unsplash.presentation.search.SearchFragment

/**
 * A simple [Fragment] subclass.
 * Use the [FeedsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedsFragment : BaseFragment<FragmentFeedsBinding>(
    inflate = FragmentFeedsBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                replace<SearchFragment>(
                    containerViewId = R.id.container_view,
                    tag = SearchFragment::class.java.simpleName
                )
            }
        }

    }
    private fun setupViewPager() {
        FeedCollectionsFragment.newInstance()
        FeedPhotosFragment.newInstance()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FeedsFragment.
         */
        // TODO: Rename and change types and number of parameters
    }
}